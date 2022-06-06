package com.example.credit_debt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText

class AddCreditDebt : AppCompatActivity() {
    protected val db = DatabaseHelper(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_credit_debt)
    }

    fun Add(v: View)
    {
        val name=findViewById<EditText>(R.id.Name).text.toString()
        val surname=findViewById<EditText>(R.id.Surname).text.toString()
        val phone=findViewById<EditText>(R.id.Phone).text.toString().toInt()
        val value=findViewById<EditText>(R.id.Value).text.toString().toFloat()
        db.insertData(name,surname,phone,value)
    }

    fun Back(v:View)
    {

    }
}