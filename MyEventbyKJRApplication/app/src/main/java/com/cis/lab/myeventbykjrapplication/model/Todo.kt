package com.cis.lab.myeventbykjrapplication.model

class ToDo {
    companion object Factory {
        fun create(): ToDo = ToDo()
    }

    var name: String? = null
    var date : String? = null
    var num : String? = null
    var email : String? = null
    var id : String? = null
    var event : String? = null
}
