package com.example.hunters

import Location_handler
import android.Manifest
import android.content.Intent
import android.location.Location
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class Hunt : AppCompatActivity() {

    lateinit var currentGPS: Location_handler;
    lateinit var database: Database;
    lateinit var loc_to_get: ArrayList<Hunt_location>;
    var disThershold = 3.0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hunt)
        currentGPS = Location_handler(this);

        database = Database(this);
        loc_to_get = database.get_locations();


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


    fun start_game() {


        var loc_counter = 0;

        NextTarget(loc_counter)
        //

    }
    private fun NextTarget(currentPos : Int) {
        if (currentPos > loc_to_get.size)
        {

            var goTo_end_hunt = Intent(this, End_hunt::class.java)
            startActivity(goTo_end_hunt)
            // logging ("success")
        return;
        }

        var img_view = findViewById<ImageView>(R.id.img_view2);

        var cur_loc = loc_to_get.get(currentPos);
        // load image
        img_view.setImageBitmap(cur_loc.get_img());

        MonitorProgress(cur_loc,currentPos)

    }

    private fun  MonitorProgress(cur_loc : Hunt_location, currentPos:Int) {
        var timer = object: CountDownTimer(250,250) {
            override fun onTick(p0: Long) {
                // check
            }
            override fun onFinish() {
                if (CheckResult(cur_loc) == true) {
                    NextTarget(currentPos + 1)
                } else {
                    MonitorProgress(cur_loc, currentPos)
                }
            }
        }
        timer.start()
    }

    private fun CheckResult(cur_loc : Hunt_location) :Boolean{
        var cur_lat = currentGPS.getLat();
        var cur_lon = currentGPS.getLon();
        Log.i("GPS", "Latitude: $cur_lat, Longitude: $cur_lon")

        // check if reached goal
        var distanceInMeters = cur_loc.calc_distance(cur_lat, cur_lon);
        Log.i("Distance", "Distance in meters: $distanceInMeters")

        if(distanceInMeters >= disThershold){
            return false;
        }
        return true;
    }


}