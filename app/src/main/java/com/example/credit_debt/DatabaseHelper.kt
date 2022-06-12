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
                COL_SURNAME + " VARCHAR(256)," +
                COL_VALUE + " FLOAT," +
                COL_DATE + " INTEGER," +
                COL_PHONE + " INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun readData():List<Person> {
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        val ls:MutableList<Person> = ArrayList()
        if(result.moveToFirst())
        {
            do {
                val name=result.getString(result.getColumnIndex(COL_NAME))
                val surname=result.getString(result.getColumnIndex(COL_SURNAME))
                val phone=result.getInt(result.getColumnIndex(COL_PHONE))
                val value=result.getFloat(result.getColumnIndex(COL_VALUE))
                val per=Person(name,surname,value,phone)
                ls.add(per)
            }while(result.moveToNext())
        }
        return ls
    }

    fun readNames():List<String>
    {
        val db = this.readableDatabase
        val query = "Select $COL_ID,$COL_NAME,$COL_SURNAME from $TABLENAME"
        val result = db.rawQuery(query, null)
        val ls:MutableList<String> = ArrayList()
        if(result.moveToFirst())
        {
            do {
                val str=""
                str.plus(result.getString(result.getColumnIndex(COL_NAME)))
                str.plus(" ")
                str.plus(result.getString(result.getColumnIndex(COL_SURNAME)))
                str.plus((" "))
                str.plus(result.getString(result.getColumnIndex(COL_ID)))
                ls.add(str)
            }while(result.moveToNext())
        }
        return ls
    }

    fun exist(per: Person): Boolean {
        val db=this.readableDatabase
        val name=per.name
        val surname=per.surname
        val phone=per.phone
        val query =
            "Select * from $TABLENAME where $COL_NAME='$name' and $COL_SURNAME='$surname'"
        val result=db.rawQuery(query,null)
        return result.moveToFirst()

    }
    fun readDebt(): MutableList<Person> {
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where $COL_VALUE>0.0"
        val result=db.rawQuery(query,null)
        val ls:MutableList<Person> = ArrayList()
        if(result.moveToFirst())
        {
            do {
                val name=result.getString(result.getColumnIndex(COL_NAME))
                val surname=result.getString(result.getColumnIndex(COL_SURNAME))
                val phone=result.getInt(result.getColumnIndex(COL_PHONE))
                val value=result.getFloat(result.getColumnIndex(COL_VALUE))
                val per=Person(name,surname,value,phone)
                ls.add(per)
            }while(result.moveToNext())
        }
        return ls
    }

    fun readCredit(): MutableList<Person> {
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where $COL_VALUE<0.0"
        val result=db.rawQuery(query,null)
        val ls:MutableList<Person> = ArrayList()
        if(result.moveToFirst())
        {
            do {
                val name=result.getString(result.getColumnIndex(COL_NAME))
                val surname=result.getString(result.getColumnIndex(COL_SURNAME))
                val phone=result.getInt(result.getColumnIndex(COL_PHONE))
                val value=result.getFloat(result.getColumnIndex(COL_VALUE))
                val per=Person(name,surname,value,phone)
                ls.add(per)
            }while(result.moveToNext())
        }
        return ls
    }

    fun readExpired(): MutableList<Person> {
        val month=2592000000
        val time=System.currentTimeMillis()
        val db=this.readableDatabase
        val query="Select * from $TABLENAME where $COL_DATE<$time-$month"
        val result=db.rawQuery(query,null)
        val ls:MutableList<Person> = ArrayList()
        if(result.moveToFirst())
        {
            do {
                val name=result.getString(result.getColumnIndex(COL_NAME))
                val surname=result.getString(result.getColumnIndex(COL_SURNAME))
                val phone=result.getInt(result.getColumnIndex(COL_PHONE))
                val value=result.getFloat(result.getColumnIndex(COL_VALUE))
                val per=Person(name,surname,value,phone)
                ls.add(per)
            }while(result.moveToNext())
        }
        return ls
    }

    fun postpone(Id:Int)
    {
        val contentValues=ContentValues()
        val db=this.writableDatabase
        contentValues.put(COL_DATE,System.currentTimeMillis())
        db.update(TABLENAME,contentValues,"COL_ID=$Id",null)
    }

    fun insertData(per:Person)
    {
        val contentValues=ContentValues().apply {
            put(COL_NAME, per.name)
            put(COL_SURNAME, per.surname)
            put(COL_VALUE, per.value)
            put(COL_DATE,System.currentTimeMillis())
            put(COL_PHONE, per.phone)
        }
        print(contentValues.isEmpty)
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
        val name=per.name
        val surname=per.surname
        val phone=per.phone
        val db=this.writableDatabase
        val query = "Select * from $TABLENAME where $COL_NAME='$name' and $COL_SURNAME='$surname' and $COL_PHONE='$phone'"
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