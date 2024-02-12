package com.example.hunters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun SelectHunt(view: View) {
        var goToSelectHunt = Intent(this, SelectHunt::class.java)
        startActivity(goToSelectHunt)
    }
}