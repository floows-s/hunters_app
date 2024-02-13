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
    lateinit var cur_loc: Location;

    companion object{
        private const val PREMISSION_REQUEST_ACCESS_LOCATION = 100;
    }


    private fun checkPermission(): Boolean {
        if(ActivityCompat.checkSelfPermission(
                cur_context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(cur_context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }else{
            return false;
        }
    }

    private fun request_permission(){
        ActivityCompat.requestPermissions(
            cur_context as Activity,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            PREMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private lateinit var locationManager : LocationManager
    public fun getLocation() {
        locationManager = cur_context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(cur_context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            val locationPermissionCode = 2
            ActivityCompat.requestPermissions(cur_context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0f, this)
    }

    private var HasGPS:Boolean = false
    private var loc : Double = 0.0
    private var lat : Double = 0.0

    public fun hasGPS() :Boolean{ return HasGPS}

    public fun getLat() : Double {return lat}
    public fun getLon() : Double {return loc}
    override fun onLocationChanged(location: Location) {
        HasGPS = true
        loc = location.longitude
        lat = location.latitude
        Log.i("gps", "la: " + location.latitude.toString());
        Log.i("gps", "lo: " + location.longitude.toString());
    }


}