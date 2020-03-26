package com.cis.lab.myeventbykjrapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_description.*

class Description : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        var name = getIntent().getStringExtra("name")
        var date = getIntent().getStringExtra("date")
        var event = getIntent().getStringExtra("event")
        var num = getIntent().getStringExtra("num")
        namelable.text = name
        datelable.text = date
        eventlable.text = event
        numlabel.text = num



    }
}
