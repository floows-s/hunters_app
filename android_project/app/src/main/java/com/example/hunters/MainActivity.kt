package com.example.hunters

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load 5 locations
        var json_loc = read_json("def_locations.json");
        val longitude = json_loc?.getDouble("longitude");
        Log.i("JOSNS", "JSON longitude: " + longitude.toString());
    }

    public fun Make_hunt(view: View){
        var goToMake_hunt = Intent(this, Make_hunt::class.java)
        startActivity(goToMake_hunt)
    }

    public fun GameHunt(view: View) {
        var goToGameHunt = Intent(this, Hunt::class.java)
        startActivity(goToGameHunt)
    }

    fun read_json(file_name: String): JSONObject? {
        val inputStream: InputStream = assets.open(file_name);
        var json_string: String = inputStream.bufferedReader().use{it.readText()};
        var json_obj = JSONObject(json_string);

        return json_obj;

    }

}