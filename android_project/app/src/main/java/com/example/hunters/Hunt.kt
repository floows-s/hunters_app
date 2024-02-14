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
    fun distance(cur_lat: Double, cur_lon: Double, loc_lat: Double, loc_lon: Double){

        val loc1 = Location("Point A")
        loc1.latitude = cur_lat
        loc1.longitude = cur_lon
        val loc2 = Location("Point B")
        loc2.latitude = loc_lat
        loc2.longitude = loc_lon

        val distanceInMeters = FloatArray(1)
        Location.distanceBetween(
        loc1.latitude, loc1.longitude,
        loc2.latitude, loc2.longitude,
        distanceInMeters
        )
        Log.i("Distance", "Distance in meters: ${distanceInMeters[0]}")

    }

    fun start_game(){
        Log.i("GPS","Latitude: ${currentGPS.getLat()}, Longitude: ${currentGPS.getLon()}")

        distance(currentGPS.getLat(), currentGPS.getLon(), 89.02, 49.22);


    }

}