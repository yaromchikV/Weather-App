package com.yaromchikv.weatherapp.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.databinding.ActivityMainBinding
import com.yaromchikv.weatherapp.ui.common.CurrentLocation
import com.yaromchikv.weatherapp.ui.common.LocationState
import com.yaromchikv.weatherapp.ui.forecast.ForecastFragment
import com.yaromchikv.weatherapp.ui.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var presenter: MainContract.Presenter

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
    }

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    private val cancellationTokenSource = CancellationTokenSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        presenter.readyToLoad()
    }

    override fun setupNavigation() {
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_today)
                changeToolbarTitle(getString(R.string.today))
        }
    }

    override fun changeToolbarTitle(text: String) {
        binding.header.text = text
    }

    //region location
    private var requestCounter = 0

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        requestCounter++

        if (requestCounter < 5) {
            if (requestCode == LOCATION_REQUEST_CODE)
                determineLocation()
        } else {
            Timber.i("Denied permission exception")

            val message = getString(R.string.error_obtain_permission)
            presenter.setLocation(LocationState.Error(message))
            presenter.onUpdateLocation()
        }
    }

    override fun determineLocation() {
        if (checkPermission()) {
            Timber.i("Permissions is granted")
            if (isGpsEnabled()) {
                Timber.i("GPS is enabled")
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        Timber.i("Last location: $location")
                        if (location == null) {
                            fusedLocationClient.getCurrentLocation(
                                PRIORITY_HIGH_ACCURACY,
                                cancellationTokenSource.token
                            ).addOnCompleteListener { task: Task<Location> ->
                                if (task.isSuccessful) {
                                    Timber.i("Current location: ${task.result}")

                                    setLocation(task.result)
                                } else {
                                    Timber.i("Current location exception: ${task.exception}")

                                    val message = getString(R.string.error_get_location)
                                    presenter.setLocation(LocationState.Error(message))
                                    presenter.onUpdateLocation()
                                }
                            }
                        } else setLocation(location)
                    }
                    .addOnFailureListener {
                        Timber.i("Last location exception: $it")

                        val message = getString(R.string.error_get_location)
                        presenter.setLocation(LocationState.Error(message))
                        presenter.onUpdateLocation()
                    }
            } else presenter.onGpsDisabled()
        }
    }

    private fun checkPermission(): Boolean {
        val isPermissionGranted = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (isPermissionGranted) return true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val isDenyFineLocation =
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
            val isDenyCoarseLocation =
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)

            if (isDenyFineLocation || isDenyCoarseLocation) {
                presenter.onPermissionDenied()
                return false
            }
        }

        requestPermission()
        return false
    }

    private fun requestPermission() {
        Timber.i("Request permission")
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), LOCATION_REQUEST_CODE
        )
    }

    private fun isGpsEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }

    private fun setLocation(location: Location) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> =
            geocoder.getFromLocation(location.latitude, location.longitude, 1)

        presenter.setLocation(
            LocationState.Ready(
                CurrentLocation(
                    location.latitude,
                    location.longitude,
                    addresses[0].locality ?: getString(R.string.unknown),
                    addresses[0].countryCode ?: getString(R.string.unknown)
                )
            )
        )
        presenter.onUpdateLocation()
    }

    override fun updateLocation() {
        val fragment = navHostFragment.childFragmentManager.fragments[0]
        when (navHostFragment.navController.currentDestination?.id) {
            R.id.navigation_today -> (fragment as WeatherFragment).updatePosition()
            R.id.navigation_forecast -> (fragment as ForecastFragment).updatePosition()
        }
    }

    private fun showDialog(title: String, message: String, errorMessage: String, func: () -> Unit) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                func()
            }
            .setOnCancelListener {
                presenter.setLocation(LocationState.Error(errorMessage))
                presenter.onUpdateLocation()
            }
            .create()
        alertDialog.show()
    }

    override fun showGpsDialog() {
        Timber.i("Dialog about turning on GPS")
        showDialog(
            getString(R.string.title_location_dialog),
            getString(R.string.message_location_dialog),
            getString(R.string.error_get_location)
        ) {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    override fun showPermissionDeniedDialog() {
        Timber.i("Denial permission dialog")
        showDialog(
            getString(R.string.title_permission_dialog),
            getString(R.string.message_permission_dialog),
            getString(R.string.error_obtain_permission)
        ) {
            requestPermission()
        }
    }
    //endregion

    companion object {
        private const val LOCATION_REQUEST_CODE = 1000
    }
}