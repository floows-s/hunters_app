package com.example.hunters

import Location_handler
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView

class Make_location : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 100;
    lateinit var database: Database;
    lateinit var loc_han: Location_handler;

    // data for location
    var lat = 0.0;
    var lon = 0.0;
    lateinit var bitmap_img: Bitmap;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_location)

        database = Database(this);
        loc_han = Location_handler(this);

        var gps_btn = findViewById<View>(R.id.btn_gps);
        gps_btn.isEnabled = false;


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
        // create and save object
        var new_location = Hunt_location(0, lat, lon, bitmap_img);


        database.insert_location(new_location)

        var goToMake_hunt = Intent(this, Make_hunt::class.java)
        startActivity(goToMake_hunt)
    }

    private fun EnableButton()
    {
        if (loc_han.hasGPS() == true)
        {
            var gps_btn = findViewById<View>(R.id.btn_gps);
            gps_btn.isEnabled = true;
        }
    }
    private fun FailedToGetGPS(){
        Log.i("gps", "failed to get gps");
    }

    public fun capture_location(view: View){
//        get data
        lat = loc_han.getLat();
        lon = loc_han.getLon();
//         show data
        var etx_la = findViewById<EditText>(R.id.etx_la);
        var etx_lo = findViewById<EditText>(R.id.etx_lo);

        etx_la.setText(lat.toString());
        etx_lo.setText(lon.toString());
    }

    public fun capture_img(view: View){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }catch (e: ActivityNotFoundException){
            //Toast.makeText(this, "Error: " + e.LocalizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var image_view: ImageView = findViewById(R.id.img_view);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            bitmap_img = data?.extras?.get("data") as Bitmap;

            image_view.setImageBitmap(bitmap_img);
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}