import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Location_handler(var cur_context: Context) {


    private lateinit var fused_location_prov_client: FusedLocationProviderClient;

    init {
        fused_location_prov_client = LocationServices.getFusedLocationProviderClient(cur_context);

        if(check_permission()){
            Log.i("test", "JA");
        }else{
            Log.i("test", "JA");
        }

    }


    public fun get_current_location(): Location{
        if(checkPermission()){
            fused_location_prov_client.lastLocation.addOnCompleteListener(cur_context){ task->
                val location: Location?=task.result
                Log.i("test". "latitude: " + location.latitude);
                Log.i("test". "longitude: " + location.longitude);

                return location;
            }
        }else{
            request_permission();
        }
    }


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
            cur_context,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                PREMISSION_REQUEST_ACCESS_LOCATION
            )
        )
    }

}