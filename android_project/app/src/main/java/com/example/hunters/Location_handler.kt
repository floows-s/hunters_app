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

class Location_handler(var cur_context: Context) : LocationListener {

    private var HasGPS:Boolean = false
    private var loc : Double = 0.0
    private var lat : Double = 0.0

    public fun hasGPS() :Boolean{ return HasGPS}
    public fun getLat() : Double {return lat}
    public fun getLon() : Double {return loc}




    private lateinit var locationManager : LocationManager
    public fun start_cap() {
        locationManager = cur_context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(cur_context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            val locationPermissionCode = 2
            ActivityCompat.requestPermissions(cur_context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0f, this)
    }

    override fun onLocationChanged(location: Location) {
        HasGPS = true;
        loc = location.longitude;
        lat = location.latitude;
    }


}