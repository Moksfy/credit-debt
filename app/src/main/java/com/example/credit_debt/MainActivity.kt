package com.example.credit_debt


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private val channelId = "i.apps.notifications"
    private val db = DatabaseHelper(this)
    var notificationId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclercred=findViewById<RecyclerView>(R.id.CreditRecycler)
        val recyclerdeb=findViewById<RecyclerView>(R.id.DebtRecycler)
        recyclercred.layoutManager=LinearLayoutManager(this)
        recyclerdeb.layoutManager=LinearLayoutManager(this)
        val ldebt=db.readDebt()
        val lcred=db.readCredit()
        for (i in db.readExpired())
           Notifier(i)
        val cred= ArrayList<String>()
        val deb= ArrayList<String>()
        for(i in ldebt)
            deb.add(i.toString())
        for(i in lcred)
            cred.add(i.toString())
        val credadpt=CustomAdapter(cred)
        val debadpt=CustomAdapter(deb)
        recyclercred.adapter=credadpt
        recyclerdeb.adapter=debadpt
    }

    fun Notifier(per:Person)
    {
        var tstring=""
        if(per.value>0)
        {
            tstring.plus("Debt: ")
            tstring.plus(per.value.toString())
        }
        else if(per.value<0)
        {
            tstring.plus("Credit: ")
            tstring.plus(per.value.toString())
        }
        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.crebtnotif)
            .setContentTitle(per.name.plus(" ".plus(per.surname)))
            .setContentText(tstring)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationId, builder.build())
            }
            notificationId++
        }
    }

    fun AddCreditDebt(v: View)
    {
        val it=Intent(this,AddCreditDebt::class.java).apply {  }
        startActivity(it)
    }

    fun RemoveCreditDebt(v:View)
    {
        val it = Intent(this, RemoveCredeb::class.java).apply { }
        startActivity(it)
    }

    fun ChangeCreditDebt(v:View)
    {
            val it = Intent(this, ChangeDebCred::class.java).apply { }
            startActivity(it)
    }

}