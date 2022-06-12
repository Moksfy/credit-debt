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
        var str=""
        str=str.plus(name)
        str=str.plus("  ")
        str=str.plus(surname)
        str=str.plus("  ")
        str=str.plus(phone.toString())
        str=str.plus("  ")
        str=str.plus(value.toString())
        return str
    }

}