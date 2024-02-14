package com.example.hunters

import Location_handler
import android.Manifest
import android.location.Location
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class Hunt : AppCompatActivity() {

    lateinit var currentGPS: Location_handler;


//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private lateinit var locationRequest: LocationRequest
//    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hunt)

        currentGPS = Location_handler(this);

        var timer = object: CountDownTimer(10000,500) {
            override fun onTick(p0: Long) {
                Log.i("GPS", "Tick")
            }
            override fun onFinish() {
                start_game()
                Log.i("GPS", "finish")
            }
        }
        timer.start()





    }

    fun start_game(){
        Log.i("GPS","Latitude: ${currentGPS.getLat()}, Longitude: ${currentGPS.getLon()}")
        

    }
//    override fun onResume() {
//        super.onResume()
//        startLocationUpdates()
//    }

//    private fun startLocationUpdates() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // Request location permissions if not granted
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
//        } else {
//            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        stopLocationUpdates()
//    }

//    private fun stopLocationUpdates() {
//        fusedLocationClient.removeLocationUpdates(locationCallback)
//    }
    //

}