package com.example.hunters

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView

class Make_hunt : AppCompatActivity() {

    lateinit var database : Database
    var loc_array = ArrayList<Hunt_location>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_hunt)

        database = Database(this);
        loc_array = database.get_locations();

        load_locBtns();



    }


    fun load_locBtns(){
        // get view and remove all childeren
        var btn_layout: LinearLayout = findViewById(R.id.btn_layout);

        var counter = 0;
        while(counter < loc_array.size){
            Log.i("hunt_location", loc_array.get(counter).toString());
            var cur_id = loc_array.get(counter).id;

            var new_btn = Button(this);

            btn_layout.addView(new_btn);

            new_btn.setText("Delete location: " + cur_id.toString());
            new_btn.setOnClickListener {
                database.delete_location(cur_id);

                // reload
                recreate();
            }

            counter++;
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