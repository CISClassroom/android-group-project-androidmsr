package com.cis.lab.myeventbykjrapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.cis.lab.myeventbykjrapplication.model.AStudent

import com.cis.lab.myeventbykjrapplication.model.ToDo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add_student.*

class AddStudentActivity : AppCompatActivity() {

    lateinit var studentList: MutableList<AStudent>
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        mDatabase = FirebaseDatabase.getInstance().reference

        Btnadd.setOnClickListener {
            addstudent("String")
        }
    }

    private fun addstudent(data: String) {

        var name1 = getIntent().getStringExtra("name")
        var newData: AStudent = AStudent.create()
        val obj = mDatabase.child("AddStudent").push()
        newData.NewName = name1.toString()
        newData.NameStudent = editText10.text.toString()
        newData.IdStudent = editText9.text.toString()
        newData.Id = obj.key
        obj.setValue(newData)

        Toast.makeText(applicationContext,"Student save successfully", Toast.LENGTH_LONG).show()
        var i = Intent(this@AddStudentActivity, Description::class.java)
        i.putExtra("name",name1)//ส่งไป showstudent
        finish()//กลับไปหน้าก่อนนี้

        }
    }

