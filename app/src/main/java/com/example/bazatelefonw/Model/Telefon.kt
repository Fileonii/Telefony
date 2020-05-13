package com.example.bazatelefonw.Model

import android.content.ContentValues
import com.example.bazatelefonw.DBHelper.DBHelper


class Telefon {
    var id:Int = 0
    var producentTelefon:String? = null
    var modelTelefon:String? = null
    var wwwTelefon:String?=null
    var wersjaTelefon:String? = null
    private lateinit var values:ContentValues
    constructor(){}

    constructor(id:Int, producentTelefon:String ,modelTelefon:String,wwwTelefon: String, wersjaTelefon:String)
    {
        this.id= id
        this.producentTelefon = producentTelefon
        this.modelTelefon = modelTelefon
        this.wwwTelefon = wwwTelefon
        this.wersjaTelefon = wersjaTelefon
    }
    public fun putValues()
    {
        values = ContentValues()
        values.put(DBHelper.COL_ID,id)
        values.put(DBHelper.COL_PRODUCENT,producentTelefon)
        values.put(DBHelper.COL_MODEL,modelTelefon)
        values.put(DBHelper.COL_WWW,wwwTelefon)
        values.put(DBHelper.COL_WERSJA,wersjaTelefon)
    }

   public fun getValues(): ContentValues {
        return values
    }
}