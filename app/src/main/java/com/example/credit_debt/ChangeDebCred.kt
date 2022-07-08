package com.example.credit_debt

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ChangeDebCred : Activity(), AdapterView.OnItemSelectedListener {
    private val db = DatabaseHelper(this)
    private lateinit var tmp :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_deb_cred)
        val spinner: Spinner =findViewById(R.id.dropdownmenuc)
        val c = db.readNames()
        try
        {
            val adp = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,c)
            adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adp
            spinner.onItemSelectedListener = this
        }
        catch(e:Exception)
        {
            finish()
        }
    }

    fun changecredeb(v:View)
    {
        try {
            val temps = tmp.split(" ")
            val timp=findViewById<EditText>(R.id.valuechange).text.toString().toFloat()
            db.changeDebCred(temps[0].toInt(),timp)
            setResult(0)
            finish()
        }
        catch (e:Exception)
        {

        }
    }

    fun backc(v: View)
    {
        finish()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            tmp = parent.getItemAtPosition(position) as String
            val temps = tmp.split(" ")
            val edittext=findViewById<EditText>(R.id.valuechange)
            edittext.setText(temps[3])
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}