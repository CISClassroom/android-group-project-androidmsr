package com.cis.lab.myeventbykjrapplication.model

public class modeldata {
    private var name: String? = null
    private var date: String? = null
    private var num: String? = null

    constructor(date: String?, name: String?, num: String?) {
        this.name = name
        this.date = date
        this.num = num
    }

    fun getdate(): String? {
        return date
    }

    fun setdate(name: String) {
        date = date
    }

    fun getname(): String? {
        return name
    }

    fun setname(message: String) {
        name = name
    }

    fun getnum(): String? {
        return num
    }

    fun setnum(uid: String) {
        num = num
    }


}
