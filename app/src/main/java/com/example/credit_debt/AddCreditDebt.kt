package com.example.credit_debt

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import java.lang.Exception

class AddCreditDebt : Activity() {
    protected val db = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_credit_debt)
    }

    fun add(v: View)
    {
        try {
            val name = findViewById<EditText>(R.id.Name).text.toString()
            val surname = findViewById<EditText>(R.id.Surname).text.toString()
            val phone = findViewById<EditText>(R.id.Phone).text.toString().toInt()
            val value = findViewById<EditText>(R.id.Value).text.toString().toFloat()
            val per=Person(name,surname,value,phone)
            if (!db.exist(per)) {
                db.insertData(per)
            }
            else
                db.changeDebCred(per)
        }
        catch(e:Exception)
        {
        }
        finish()
    }

    fun back(v:View)
    {
        finish()
    }
}