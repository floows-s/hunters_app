package com.example.hunters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

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