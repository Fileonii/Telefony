package com.example.bazatelefonw.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.bazatelefonw.Model.Telefon
import com.example.bazatelefonw.R
import kotlinx.android.synthetic.main.row_layout.view.*


class ListTelefonyAdapter(internal var activity:Activity,
                          internal var lstTelefony:List<Telefon>,
                          internal var id: EditText,
                          internal var wersja: EditText,
                          internal var www: EditText,
                          internal var model: EditText,
                          internal var producent: EditText):BaseAdapter() {
    internal var inflater:LayoutInflater
    init{
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val rowView:View
        rowView=inflater.inflate(R.layout.row_layout,null)
        rowView.textModel.text = lstTelefony[position].modelTelefon.toString()
        rowView.textProducent.text = lstTelefony[position].producentTelefon.toString()
        rowView.textWersja.text = lstTelefony[position].wersjaTelefon.toString()
        rowView.textWww.text = lstTelefony[position].wwwTelefon.toString()
        rowView.textID.text = lstTelefony[position].id.toString()
        //event
        rowView.setOnClickListener {
            wersja.setText( rowView.textWersja.text.toString())
            model.setText( rowView.textModel.text.toString())
            producent.setText( rowView.textProducent.text.toString())
            www.setText(rowView.textWww.text.toString())
            id.setText(rowView.textID.text.toString())
        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return lstTelefony[position]
    }

    override fun getItemId(position: Int): Long {
      return lstTelefony[position].id.toLong()
    }

    override fun getCount(): Int {
        return lstTelefony.size
    }
}