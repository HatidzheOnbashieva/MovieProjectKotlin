package com.example.movieprojectkotlin.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseConnectivity(context: Context): SQLiteOpenHelper(context, DATABASE_NAME , null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "movies.db"
        const val TABLE_NAME = "favourite_movie_info"
        const val COL1 = "movieID"
        const val COL2 = "movieTitle"
        const val COL3 = "movieOverview"
        const val COL4 = "movieOriginalTitle"
        const val COL5 = "movieRating"
        const val COL6 = "movieReleaseDate"
        const val COL7 = "movieImageURL"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //PRIMARY KEY AUTOINCREMENT
        val createTable: String =
            "CREATE TABLE $TABLE_NAME (movieID INTEGER, movieTitle TEXT, movieOverview TEXT, movieOriginalTitle TEXT, movieRating NUMERIC, movieReleaseDate TEXT, movieImageURL TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }


    fun addData(movieID: Int, title: String, overview: String, originalTitle: String, rating: Double, releaseDate: String, imageURL: String): Boolean{

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL1, movieID)
        values.put(COL2, title)
        values.put(COL3, overview)
        values.put(COL4, originalTitle)
        values.put(COL5, rating)
        values.put(COL6, releaseDate)
        values.put(COL7, imageURL)

        val result = db.insert(TABLE_NAME, null, values)

        return result != -1L

    }

    fun removeData(id: Int): Boolean{
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "movieID = $id", null) > 0
    }

    fun getContents(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COL2", null)
    }
}