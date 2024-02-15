package com.example.hunters

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.graphics.createBitmap
import java.io.ByteArrayOutputStream
import java.util.Arrays

class Database(var app_context: Context) {

    private lateinit var locationsDB: SQLiteDatabase

    init {
        open_db()

    }

    public fun open_db() {
        locationsDB = app_context.openOrCreateDatabase("locationDB", Context.MODE_PRIVATE, null)

        // create table if it isnt created already (sqlite)
        locationsDB.execSQL("CREATE TABLE IF NOT EXISTS locations (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude DOUBLE, longitude DOUBLE, bitmap_img BLOB);")
    }

    public fun add_default(){

    }

    public fun delete_all(){
        locationsDB.execSQL("DELETE FROM locations");
    }
    fun insert_location(location: Hunt_location): Int {
        Log.i("ID TEST", "insert");
        var latitude = location.latitude;
        var longitude = location.longitude;
        var bitmap_img = location.img_bitmap;
        val blob_img = ByteArrayOutputStream().apply { bitmap_img.compress(Bitmap.CompressFormat.PNG, 100, this) }.toByteArray();

        var result = Arrays.toString(blob_img);

        var ins = locationsDB.compileStatement("INSERT INTO locations (latitude, longitude, bitmap_img) VALUES(?,?,?)")
        ins.clearBindings()
        ins.bindDouble(1,latitude)
        ins.bindDouble(2,longitude)
        ins.bindBlob(3,blob_img)
        var l = ins.executeInsert()
        var t =0

//        locationsDB.execSQL("INSERT INTO locations (latitude, longitude, bitmap_img) VALUES ('$latitude', '$longitude', $blob_img');")


        return 0
    }

    fun get_locations(): ArrayList<Hunt_location> {
        Log.i("ID TEST", "WERKT get location");
        var loc_array = ArrayList<Hunt_location>();
        val loc_cur: Cursor = locationsDB.rawQuery("SELECT * FROM locations",null)
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

            val id_string = id.toString();
            val lat_string = latitude.toString();

            Log.i("ID TEST", "id: $id_string");
            Log.i("ID TEST", "lat: $lat_string");

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