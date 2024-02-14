package com.example.hunters

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory

class Database(var app_context: Context) {

    private lateinit var locationsDB: SQLiteDatabase

    init {
        open_db()
    }

    private fun open_db() {
        locationsDB = app_context.openOrCreateDatabase("gebruikersDB", Context.MODE_PRIVATE, null)

        // create table if it isnt created already (sqlite)
        locationsDB.execSQL("CREATE TABLE IF NOT EXISTS locations (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude DOUBLE, longitude DOUBLE, bitmap_img BLOB);")
    }

    fun insert_location(location: Hunt_location): Int {
        var latitude = location.latitude;
        var longitude = location.longitude;
        var bitmap_img = location.img_bitmap;

        locationsDB.execSQL("INSERT INTO gegevens (latitude, longitude, bitmap_img) VALUES ('$latitude', '$longitude', '$bitmap_img');")
        return 0
    }

    fun get_locations(): ArrayList<Hunt_location> {
        var id: Int
        var loc_array = ArrayList<Hunt_location>();
        val loc_cur: Cursor = locationsDB.rawQuery("SELECT * FROM locations", null)
        loc_cur.moveToFirst()

        val colum_id = loc_cur.getColumnIndex("id")
        val colum_latitude = loc_cur.getColumnIndex("latitude")
        val colum_longitude = loc_cur.getColumnIndex("longitude")
        val colum_bitmap_img = loc_cur.getColumnIndex("bitmap_img")

        while (loc_cur.moveToNext()) {
            val id = loc_cur.getInt(colum_id);
            val latitude = loc_cur.getDouble(colum_latitude)
            val longitude = loc_cur.getDouble(colum_longitude)

            val byte_array = loc_cur.getBlob(colum_bitmap_img)
            val bitmap_img = BitmapFactory.decodeByteArray(byte_array, 0, byte_array.size);

            var new_location = Hunt_location(id, latitude, longitude, bitmap_img);

            loc_array.add(new_location);
        }

        loc_cur.close()
        return loc_array;
    }


    fun delete_location(id: Int){
        locationsDB.execSQL("DELETE FROM locations WHERE id = $id")
    }
}