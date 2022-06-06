package com.example.credit_debt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val per=Person()
        per.name=findViewById<EditText>(R.id.Name).text.toString()
        per.surname=findViewById<EditText>(R.id.Surname).text.toString()
        per.phone=findViewById<EditText>(R.id.Phone).text.toString().toInt()
        per.value=findViewById<EditText>(R.id.Value).text.toString().toFloat()
        if(db.exist(per))
        db.insertData(per)
        else
        db.changeDebCred(per)
    }

    fun Back(v:View)
    {

    }
}