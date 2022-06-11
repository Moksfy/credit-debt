package com.example.credit_debt

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChangeDebCred : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_deb_cred)
    }

    fun changecredeb(v:View)
    {

        finish()
    }

    fun backc(v: View)
    {
        finish()
    }
}