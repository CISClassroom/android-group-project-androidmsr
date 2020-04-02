package com.cis.lab.myeventbykjrapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.cis.lab.myeventbykjrapplication.model.ToDo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_result.*

class AddActivity : AppCompatActivity() {

    private val TAG:String = "add activity"

    var valuespinner = " "
    var mAuth: FirebaseAuth? = null
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        mDatabase = FirebaseDatabase.getInstance().reference

        val spinner: Spinner = findViewById(R.id.spinner)
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("ด้านที่ 1 การพัฒนาศักยภาพตนเอง")
        arrayList.add("ด้านที่ 2 การธำรงไว้ซึ่งสถาบันชาติ ศาสนา พระมหากษัตริย์ เสริมสร้างจิตสำนึกความภาคภูมิใจในมหาวิทยาลัยและคณะ")
        arrayList.add("ด้านที่ 3 การเสริมสร้างจิตอาสา และจิตสาธารณะ")
        arrayList.add("ด้านที่ 4 การสร้างคุณธรรมจริยธรรมและศีลธรรม")
        arrayList.add("ด้านที่ 5 การอนุรักษ์ศิลปวัฒนธรรมไทยและภูมิปัญญาท้องถิ่น")
        val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter


        button4.setOnClickListener {
            savedata()
//            val intent = Intent(this@AddActivity, AddStudentActivity::class.java)
//            intent.putExtra("email",textView5.text.toString())
//            startActivity(intent)
        }
    }
    private fun savedata(){
        var name = editText3.text.toString().trim()
        var date = editText4.text.toString().trim()
        var num = editText5.text.toString().trim()
        var event =  spinner.selectedItem.toString().trim()

        if (name.isEmpty()){
            editText3.error = "Please Enter a name1"
            return
        }
       else if (date.isEmpty()) {
            editText4.error = "Please Enter a name2"
            return
         }else if (num.isEmpty()){
        editText5.error = "Please Enter a name3"
        return
        }
        if (event.isEmpty()){
            Toast.makeText(this,"Please Enter a name4",Toast.LENGTH_SHORT).show()
            return
        }
        var  todoItem = ToDo.create()
        var email = getIntent().getStringExtra("email")
        val newItem = mDatabase.child("Student").push()
        // add new key to todoobject
        todoItem.id = newItem.key
        todoItem.name = name
        todoItem.date = date
        todoItem.num = num
        todoItem.email = email
        todoItem.event = event
        newItem.setValue(todoItem)
        finish()
    }
}


