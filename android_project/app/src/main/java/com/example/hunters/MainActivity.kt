package com.example.hunters

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    lateinit var database: Database;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Database(this);

        val PREFS_NAME = "MyPrefsFile"
        val settings = getSharedPreferences(PREFS_NAME, 0)
        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time")

            // first time task
            var all_locations = database.get_locations();

            if(all_locations.size == 0){
                Log.i("db", "geen locaties");
                // load 5 locations
                var img_1: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.foto_1);
                var img_2: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.foto_2);
                var img_3: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.foto_3);

                var hl_1 = Hunt_location(0, 50.3, 20.4, img_1);
                var hl_2 = Hunt_location(0, 20.2,32.4, img_2);
                var hl_3 = Hunt_location(0, 40.2, 10.3, img_3);

                database.insert_location(hl_1);
                database.insert_location(hl_2);
                database.insert_location(hl_3);

            }

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit()
        }




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