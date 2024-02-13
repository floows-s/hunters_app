package com.example.hunters

import Location_handler
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View

class Make_location : AppCompatActivity() {

    var loc_han = Location_handler(this);


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_location)
        loc_han.getLocation();

        var timer = object: CountDownTimer(10000,500) {
            override fun onTick(p0: Long) {
                EnableButton()
            }
            override fun onFinish() {
                FailedToGetGPS()
            }
        }
        timer.start()
    }
    public fun BackTo_make_hunt_klaar(view: View){
        var goToMake_hunt = Intent(this, Make_hunt::class.java)
        startActivity(goToMake_hunt)
    }

    private fun EnableButton()
    {
        if (loc_han.hasGPS() == true)
        {
            var button = findViewById<View>(R.id.btn_gps);
            button.isEnabled = true;
        }
    }
    private fun FailedToGetGPS(){

    }

    public fun get_location(view: View){

        var d = loc_han.getLat()
        var sd = loc_han.getLat()
    }
}