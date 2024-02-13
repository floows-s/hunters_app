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

    public fun Make_hunt(view: View){
        var goToMake_hunt = Intent(this, Make_hunt::class.java)
        startActivity(goToMake_hunt)
    }

    public fun GameHunt(view: View) {
        var goToGameHunt = Intent(this, Hunt::class.java)
        startActivity(goToGameHunt)
    }
}