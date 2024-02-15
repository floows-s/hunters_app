package com.example.hunters

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class End_hunt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_hunt)
    }
    public fun BackTo_main_klaar(view: View){

        var goTo_main = Intent(this, MainActivity::class.java)
        startActivity(goTo_main)
    }
}