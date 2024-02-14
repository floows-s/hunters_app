package com.example.hunters

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Hunt_location(var latitude: Double, var longitude: Double, var img_bitmap: Bitmap) {



    public fun calc_distance(lat_p2: Double, lon_p2: Double){
        var lat_p1 = latitude;
        var lon_p1 = longitude;



    }


    public fun get_img(): Bitmap{

        return img_bitmap;
    }



}