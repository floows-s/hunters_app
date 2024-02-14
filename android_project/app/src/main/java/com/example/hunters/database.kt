package com.example.hunters

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class database(context: Context) {

    private var locationsDB: SQLiteDatabase? = null
    private var app_context: Context? = null

    init {
        app_context = context
        open_db()
    }

    private fun open_db() {
        locationsDB = app_context?.openOrCreateDatabase("gebruikersDB", Context.MODE_PRIVATE, null)

        // create table if it isnt created already (sqlite)
        locationsDB?.execSQL("CREATE TABLE IF NOT EXISTS locations (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude DOUBLE, longitude DOUBLE, bitmap_img BLOB);")
    }

    fun insert_location(location: Hunt_location): Int {
        var latitude = location.latitude;
        var longitude = location.longitude;
        var bitmap_img = location.img_bitmap;

        locationsDB?.execSQL("INSERT INTO gegevens (latitude, longitude, bitmap_img) VALUES ('$latitude', '$longitude', '$bitmap_img');")
        return 0
    }

    fun get_locations() {
        val loc_cur: Cursor? = locationsDB?.rawQuery("SELECT * FROM locations", null)
        loc_cur.moveToFirst()
        val colum_inlognaam = loc_cur.getColumnIndex("latitude")
        val colum_wachtwoord = loc_cur.getColumnIndex("longitude")
        val colum_bitmap_img
        while (loc_cur.moveToNext()) {
            val users = loc_cur.getString(colum_inlognaam)
            val wachtwoord = loc_cur.getString(colum_wachtwoord)
        }
        loc_cur.close()
    }
}