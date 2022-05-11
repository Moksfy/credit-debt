package com.example.credit_debt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    val GROUP_KEY_DEBT_CREDIT ="CREDBT"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Notifier(Name:String,Value:Float)
    {
        var tstring="c"
        if(Value>0)
            tstring="Debt"+Value.toString()
        else if(Value<0)
            tstring="Credit"+Value.toString()
        val CHANNEL_ID="0"
        val newMessageNotification = NotificationCompat.Builder(this@MainActivity, CHANNEL_ID)
            .setSmallIcon(R.drawable.crebtnotif)
            .setContentTitle(Name)
            .setContentText(tstring)
            //.setLargeIcon(emailObject.getSenderAvatar())
            .setGroup(GROUP_KEY_DEBT_CREDIT)
            //.addAction()
            .build()
    }
}