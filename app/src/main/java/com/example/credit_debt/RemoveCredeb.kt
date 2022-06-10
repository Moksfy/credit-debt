package com.example.credit_debt

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.Spinner

class RemoveCredeb : Activity(), AdapterView.OnItemSelectedListener {
    protected val db = DatabaseHelper(this);
    lateinit var tmp :Any
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_credeb)
        val spinner:Spinner=findViewById(R.id.dropdownmenu)
        val c:List<Person>
        c=db.readData()
            val adp = ArrayAdapter<Person>(this, R.id.dropdownmenu, c)
            spinner.adapter=adp
            spinner.onItemSelectedListener=this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0 != null) {
            tmp=p0.getItemAtPosition(p2)

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}