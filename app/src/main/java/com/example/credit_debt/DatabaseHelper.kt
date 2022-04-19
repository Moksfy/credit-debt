package com.example.credit_debt

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "CreditDebt.db"
    val TABLENAME = "People"
    val COL_NAME = "name"
    val COL_SURNAME = "surname"
    val COL_ID = "id"
    val COL_VALUE = "value"
    private val DATABASE_ALTER_TABLE_1 = ("ALTER TABLE "
            + TABLENAME) + " ADD COLUMN " + COL_VALUE + " float;"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_SURNAME + " VARCHAR(256))"+
                COL_VALUE +"FLOAT"
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

    fun insertData()
    {
        val contentValues=ContentValues()
        TODO("data input required")
        contentValues.put(COL_NAME,"")
        contentValues.put(COL_SURNAME,"")
        contentValues.put(COL_VALUE,0)
        val db=this.writableDatabase
        val result=db.insert(TABLENAME,null,contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
}