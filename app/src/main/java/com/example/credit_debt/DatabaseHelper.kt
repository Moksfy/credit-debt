package com.example.credit_debt

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.SystemClock
import android.widget.Toast
import java.util.*

class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, "CreditDebt.db", null, 1)
{
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "CreditDebt.db"
    val TABLENAME = "People"
    val COL_NAME = "name"
    val COL_SURNAME = "surname"
    val COL_ID = "id"
    val COL_VALUE = "value"
    val COL_DATE = "date"
    private val DATABASE_ALTER_TABLE_1 = ("ALTER TABLE "
            + TABLENAME) + " ADD COLUMN " + COL_VALUE + " float;"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_SURNAME + " VARCHAR(256))"+
                COL_VALUE +"FLOAT"+
                COL_DATE +"LONG"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun readData() {
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
    }

    fun readDebt()
    {
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where COL_VALUE>0"
        val result=db.rawQuery(query,null)
    }

    fun readCredit()
    {
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where COL_VALUE<0"
        val result=db.rawQuery(query,null)
    }

    fun readExpired()
    {
        val month=2592000000
        val time=SystemClock.currentThreadTimeMillis()
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where $time-COL_DATE>$month"
        val result=db.rawQuery(query,null)
    }

    fun postpone(Id:Int)
    {
        val contentValues=ContentValues()
        val db=this.writableDatabase
        contentValues.put(COL_DATE,SystemClock.currentThreadTimeMillis())
        val result=db.update(TABLENAME,contentValues,"COL_ID=$Id",null)
    }

    fun insertData()
    {
        val contentValues=ContentValues()
        TODO("data input required")
        contentValues.put(COL_NAME,"")
        contentValues.put(COL_SURNAME,"")
        contentValues.put(COL_VALUE,0)
        contentValues.put(COL_DATE,SystemClock.currentThreadTimeMillis())
        val db=this.writableDatabase
        val result=db.insert(TABLENAME,null,contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun RemoveDebCred(Id:Int)
    {
        val contentValues=ContentValues()
        val db=this.writableDatabase
        contentValues.put(COL_VALUE,0)
        val result=db.update(TABLENAME,contentValues,"COL_ID=$Id",null)
    }

    fun ChangeDebCred(Id:Int,Nval:Int)
    {
        val contentValues=ContentValues()
        val db=this.writableDatabase
        contentValues.put(COL_VALUE,Nval)
        val result=db.update(TABLENAME,contentValues,"COL_ID=$Id",null)
    }
}