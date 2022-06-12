package com.example.credit_debt

class Person
{
    constructor(nae: String, sur: String, va: Float, ph: Int)
    {
            name=nae
            surname=sur
            phone=ph
            value=va
    }

    var name=""
    var surname=""
    var phone=0
    var value:Float= 0F



    override fun toString(): String {
        val str=""
        str.plus(name)
        str.plus("  ")
        str.plus(surname)
        str.plus("  ")
        str.plus(phone.toString())
        str.plus("  ")
        str.plus(value.toString())
        return str
    }

}