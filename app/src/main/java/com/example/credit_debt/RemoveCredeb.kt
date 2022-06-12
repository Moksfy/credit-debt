package com.example.credit_debt

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

 class RemoveCredeb : Activity(), AdapterView.OnItemSelectedListener {
    private val db = DatabaseHelper(this)
     private lateinit var tmp :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_credeb)
        val spinner:Spinner=findViewById(R.id.dropdownmenu)
        val c:List<String> = db.readNames()
            val adp = ArrayAdapter<String>(this, R.id.dropdownmenu, c)
            spinner.adapter=adp
            spinner.onItemSelectedListener=this

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0 != null) {
            tmp=p0.getItemAtPosition(p2).toString()

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun removeCredeb(v:View)
    {
        try {
            val temps = tmp.split(" ")
            db.removeDebCred(temps[2].toInt())
            finish()
        }
        catch (e:Exception)
        {

        }
    }

    fun backr(v:View)
    {
        finish()
    }
}
