package com.example.hunters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Make_hunt : AppCompatActivity() {

    var database = Database(this);
    var loc_array = ArrayList<Hunt_location>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_hunt)

        // get all data
        loc_array = database.get_locations();
        for (i in 1..5) {
            println(i)
        }
    }

    public fun Make_location(view: View){
        var goToMake_location = Intent(this, Make_location::class.java)
        startActivity(goToMake_location)
    }
    public fun MainActivity(view: View){
        var goToMainActivity = Intent(this, MainActivity::class.java)
        startActivity(goToMainActivity)
    }
}