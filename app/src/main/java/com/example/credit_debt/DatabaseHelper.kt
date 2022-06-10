package com.example.credit_debt

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.SystemClock
import android.widget.Toast

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
    val COL_PHONE = "phone"
    private val DATABASE_ALTER_TABLE_1 = ("ALTER TABLE "
            + TABLENAME) + " ADD COLUMN " + COL_VALUE + " float;"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_SURNAME + " VARCHAR(256),"+
                COL_VALUE +"REAL,"+
                COL_DATE +"INTEGER,"+
                COL_PHONE +"INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun readData():List<Person> {
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        val ls:List<Person>
        ls=emptyList()
        if(result.moveToFirst())
        {
        }
        return ls
    }

    fun exist(per: Person): Boolean {
        val db=this.readableDatabase
        val name=per.name
        val surname=per.surname
        val phone=per.phone
        val query = "Select * from $TABLENAME where $COL_NAME=$name and $COL_SURNAME=$surname and $COL_PHONE=$phone"
        val result=db.rawQuery(query,null)
        return result.moveToFirst()

    }
    fun readDebt()
    {
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where COL_VALUE>0"
        val result=db.rawQuery(query,null)
        if(result.moveToFirst())
        {

        }
    }

    fun readCredit()
    {
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where COL_VALUE<0"
        val result=db.rawQuery(query,null)
        if(result.moveToFirst())
        {

        }
    }

    fun readExpired()
    {
        val month=2592000000
        val time=SystemClock.currentThreadTimeMillis()
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where $time-COL_DATE>$month"
        val result=db.rawQuery(query,null)
        if(result.moveToFirst())
        {

        }
    }

    fun postpone(Id:Int)
    {
        val contentValues=ContentValues()
        val db=this.writableDatabase
        contentValues.put(COL_DATE,SystemClock.currentThreadTimeMillis())
        db.update(TABLENAME,contentValues,"COL_ID=$Id",null)
    }

    fun insertData(per:Person)
    {
        val contentValues=ContentValues()
        contentValues.put(COL_NAME,per.name)
        contentValues.put(COL_SURNAME,per.surname)
        contentValues.put(COL_VALUE,per.value)
        contentValues.put(COL_DATE,SystemClock.currentThreadTimeMillis())
        contentValues.put(COL_PHONE,per.phone)
        val db=this.writableDatabase
        val result=db.insert(TABLENAME,null,contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeDebCred(Id:Int)
    {
        val contentValues=ContentValues()
        val db=this.writableDatabase
        contentValues.put(COL_VALUE,0)
        db.update(TABLENAME,contentValues,"COL_ID=$Id",null)
    }

    fun changeDebCred(Id:Int,Nval:Int)
    {
        val contentValues=ContentValues()
        val db=this.writableDatabase
        contentValues.put(COL_VALUE,Nval)
        db.update(TABLENAME,contentValues,"COL_ID=$Id",null)
    }

    fun changeDebCred(per:Person)
    {
        val contentValues=ContentValues()
        val db=this.writableDatabase
        val query = "Select * from $TABLENAME where COL_NAME=$per.name and COL_SURNAME=$per.surname and COL_PHONE=$per.phone"
        val result=db.rawQuery(query,null)
        if(result.moveToFirst())
        {
            val colindex=result.getColumnIndex(COL_ID)
            val id=result.getInt(colindex)
            contentValues.put(COL_VALUE, per.value)
            db.update(TABLENAME, contentValues, "COL_ID=$id", null)
        }
    }
}