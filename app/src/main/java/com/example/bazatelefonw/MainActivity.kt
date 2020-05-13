package com.example.bazatelefonw

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.bazatelefonw.Adapter.ListTelefonyAdapter
import com.example.bazatelefonw.DBHelper.DBHelper
import com.example.bazatelefonw.DBHelper.Provider
import com.example.bazatelefonw.Model.Telefon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var lstTelefony:List<Telefon> = ArrayList<Telefon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)
        refreshData()

        //Event

        add.setOnClickListener{
            val telefon  = Telefon(
                Integer.parseInt(id.text.toString()),
                producent.text.toString(),
                model.text.toString(),
                www.text.toString(),
                wersja.text.toString())

            db.addTelefon(telefon)
            refreshData()


          //  telefon.putValues()
         //   var newUri : Uri? = contentResolver.insert(Provider.URI_ZAWARTOSCI, telefon.getValues())
            //TU SKONCZYLEM!!!!!


        }
        update.setOnClickListener{
            val telefon  = Telefon(
                Integer.parseInt(id.text.toString()),
                producent.text.toString(),
                model.text.toString(),
                www.text.toString(),
                wersja.text.toString())

            db.updateTelefon(telefon)
            refreshData()
        }

        delete.setOnClickListener{
            val telefon  = Telefon(
                Integer.parseInt(id.text.toString()),
                producent.text.toString(),
                model.text.toString(),
                www.text.toString(),
                wersja.text.toString())

            db.deleteTelefon(telefon)
            refreshData()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId)
        {

        }
        return super.onOptionsItemSelected(item)
    }

    private fun refreshData()
    {
        lstTelefony = db.allTelefon
        val adapter = ListTelefonyAdapter(this,lstTelefony,id,wersja,www,model,producent)
        list_telefon.adapter = adapter
    }


}
