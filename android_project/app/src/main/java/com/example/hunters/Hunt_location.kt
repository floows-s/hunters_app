package com.example.hunters

import android.graphics.Bitmap
import android.health.connect.datatypes.ExerciseRoute.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Hunt_location(var id: Int, var latitude: Double, var longitude: Double, var img_bitmap: Bitmap) {

//    public static void distanceBetween (double startLatitude,
//    double startLongitude,
//    double endLatitude,
//    double endLongitude,
//    float[] results)
//
//    public float distanceTo (Location dest)
    public fun calc_distance(lat_p2: Double, lon_p2: Double): Float{
        var lat_p1 = latitude;
        var lon_p1 = longitude;

        val loc1 = android.location.Location("Point A")
        loc1.latitude = lat_p2
        loc1.longitude = lon_p2
        val loc2 = android.location.Location("Point B")
        loc2.latitude = latitude
        loc2.longitude = longitude

        val distanceInMeters = FloatArray(1)
        android.location.Location.distanceBetween(
            loc1.latitude, loc1.longitude,
            loc2.latitude, loc2.longitude,
            distanceInMeters
        )

        return distanceInMeters[0]
    }


    public fun get_img(): Bitmap{

        return img_bitmap;
    }



}