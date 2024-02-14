import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.Manifest
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

class Location_handler(var cur_context: Context) {

    private var HasGPS:Boolean = false
    private var lon : Double = 0.0
    private var lat : Double = 0.0

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    public fun hasGPS() :Boolean{ return HasGPS}
    public fun getLat() : Double {return lat}
    public fun getLon() : Double {return lon}

    init{
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(cur_context)
        locationRequest = LocationRequest.create().apply {
            interval = 1000 // Update location every second
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult ?: return
                for (location in locationResult.locations){
                    HasGPS = true;
                    // Get latitude and longitude
                    lat = location.latitude
                    lon = location.longitude
                    // Do something with the location data
//                    println("Latitude: $lat, Longitude: $lon")
//                    Log.i("GPS","Latitude: $lat, Longitude: $lon")

                }
            }
        }

        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(cur_context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permissions if not granted
            ActivityCompat.requestPermissions(cur_context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        } else {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        }
    }

}