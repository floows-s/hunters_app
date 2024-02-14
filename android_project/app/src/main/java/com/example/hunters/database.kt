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
        // check if user exsits
        val user_cur: Cursor = gebruikersDB.rawQuery("SELECT inlognaam FROM gegevens", null)
        user_cur.moveToFirst()
        val colum_inlognaam = user_cur.getColumnIndex("inlognaam")
        while (user_cur.moveToNext()) {
            val fetched_username = user_cur.getString(colum_inlognaam)
            if (fetched_username == username) {
                user_cur.close()
                return -1 // error user already exists
            }
        }
        user_cur.close()

        // if user doesnt exists
        gebruikersDB.execSQL("INSERT INTO gegevens (inlognaam, wachtwoord) VALUES ('$username', '$password');")
        return 0
    }
}