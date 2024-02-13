package com.example.hunters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Make_location : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_location)
    }
    public fun BackTo_make_hunt_klaar(view: View){
        var goToMake_hunt = Intent(this, Make_hunt::class.java)
        startActivity(goToMake_hunt)
    }
}