package com.example.bazatelefonw.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.bazatelefonw.Model.Telefon

private val TAG = "DBHelper"

open class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER) {


    companion object{
        const val DATABASE_VER = 1
        const val DATABASE_NAME = "EDMTDB.db"

        const val TABLE_NAME = "Telefon"
        const val COL_ID= "ID"
        const val COL_PRODUCENT = "Producent"
        const val COL_MODEL = "Model"
        const val COL_WWW = "WWW"
        const val COL_WERSJA = "WersjaAndroid"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE  $TABLE_NAME ( $COL_ID INTEGER PRIMARY KEY, $COL_PRODUCENT TEXT, $COL_MODEL TEXT, $COL_WWW TEXT, $COL_WERSJA TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    val allTelefon : List<Telefon>
        get() {
            val lstTelefony = ArrayList<Telefon>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery,null)
            if(cursor.moveToFirst())
            {
                do{
                    val telefon = Telefon()
                    telefon.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    telefon.producentTelefon = cursor.getString(cursor.getColumnIndex(COL_PRODUCENT))
                    telefon.modelTelefon = cursor.getString(cursor.getColumnIndex(COL_MODEL))
                    telefon.wwwTelefon = cursor.getString(cursor.getColumnIndex(COL_WWW))
                    telefon.wersjaTelefon = cursor.getString(cursor.getColumnIndex(COL_WERSJA))

                    lstTelefony.add(telefon)
                }while( cursor.moveToNext())
            }
            db.close()
            return lstTelefony
        }
    fun addTelefon(telefon:Telefon)
    {
        Log.d(TAG,"wykonano $")
        val temp = telefon.id.toString()
        val temp2 = telefon.producentTelefon.toString()
        Log.d(TAG, "$temp, $temp2")


        val db =  this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,telefon.id)
        values.put(COL_PRODUCENT,telefon.producentTelefon)
        values.put(COL_MODEL,telefon.modelTelefon)
        values.put(COL_WWW,telefon.wwwTelefon)
        values.put(COL_WERSJA,telefon.wersjaTelefon)
        db.insert(TABLE_NAME,null,values)
        db.close()


    }


    fun updateTelefon(telefon:Telefon):Int
    {
        val db =  this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,telefon.id)
        values.put(COL_PRODUCENT,telefon.producentTelefon)
        values.put(COL_MODEL,telefon.modelTelefon)
        values.put(COL_WWW,telefon.wwwTelefon)
        values.put(COL_WERSJA,telefon.wersjaTelefon)
        return db.update(TABLE_NAME,values,"$COL_ID=?",arrayOf(telefon.id.toString()))
    }

    fun deleteTelefon(telefon:Telefon):Int
    {
        val db =  this.writableDatabase

        return db.delete(TABLE_NAME,"$COL_ID=?",arrayOf(telefon.id.toString()))
        db.close()
    }


}