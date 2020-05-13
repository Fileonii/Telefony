package com.example.bazatelefonw.DBHelper

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.content.UriMatcher
import android.util.Log
import java.lang.IllegalArgumentException

private val TAG = "Provider"
class Provider:ContentProvider() {

    private lateinit var dbHelper:DBHelper
    companion object {
        private val PROVIDER_ID = "com.example.bazatelefonw.DBHelper.Provider"
        public val URI_ZAWARTOSCI =
            Uri.parse("content://" + PROVIDER_ID + "/" + DBHelper.TABLE_NAME)
    }
    private val TABLE = 1
    private  val ROW = 2
     val uriMatcher by lazy{ buildUriMatcher()}

    fun buildUriMatcher(): UriMatcher{
        Log.d(TAG, "buildUriMatcher:rozpoczyna sie")

        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        matcher.addURI(PROVIDER_ID, DBHelper.TABLE_NAME, TABLE)
        uriMatcher.addURI(PROVIDER_ID, DBHelper.TABLE_NAME + "/#",ROW)
        return matcher
    }

    override fun onCreate(): Boolean {

        dbHelper = context?.let { DBHelper(it) }!!
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val typUri = uriMatcher.match(uri)
        dbHelper = context?.let { DBHelper(it) }!!
       val DB  =  dbHelper.writableDatabase
        var cursor: Cursor? = null

        when(typUri){
            TABLE -> {cursor = DB.query(true, DBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder, null);}
            ROW ->{}
            else -> {throw IllegalArgumentException("Nieznane URI: $uri")}
        }

        cursor?.setNotificationUri( context!!.contentResolver, uri)

        return cursor


    }

    override fun getType(uri: Uri): String? {
        return null
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var uriType = uriMatcher.match(uri)
        dbHelper = context?.let { DBHelper(it) }!!
        val DB  =  dbHelper.writableDatabase

        if (uriType == TABLE){
            DB.insert(DBHelper.TABLE_NAME,null,values)
        }
        else{
            throw IllegalArgumentException("Unknown URI:$uri")

        }

        context!!.contentResolver.notifyChange(uri,null)
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var uriType = uriMatcher.match(uri)
        dbHelper = context?.let { DBHelper(it) }!!
        val DB  =  dbHelper.writableDatabase

        if (uriType == TABLE){
            DB.delete(DBHelper.TABLE_NAME,selection,selectionArgs)
        }
        else{
            throw IllegalArgumentException("Unknown URI:$uri")

        }
        context!!.contentResolver.notifyChange(uri,null)

        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        var uriType = uriMatcher.match(uri)
        dbHelper = context?.let { DBHelper(it) }!!
        val DB  =  dbHelper.writableDatabase

        if(uriType == TABLE){
            DB.update(DBHelper.TABLE_NAME, values, selection, selectionArgs);
        }
        else{
            throw IllegalArgumentException("Unknown URI:$uri ")
        }
        context!!.contentResolver.notifyChange(uri,null)

        return 0
    }




}