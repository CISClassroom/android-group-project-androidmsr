package com.cis.lab.myeventbykjrapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.cis.lab.myeventbykjrapplication.Adapter.ToDoItemAdapter
import com.cis.lab.myeventbykjrapplication.Adapter.ToDoStudentAdapter
import com.cis.lab.myeventbykjrapplication.model.AStudent
import com.cis.lab.myeventbykjrapplication.model.ToDo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_description.*

class Description : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    lateinit var adapter: ToDoStudentAdapter
    private var listViewItems: ListView? = null
    var toDoStudentList: MutableList<AStudent>? = null

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

        val goActivity: Button = findViewById(R.id.button6)

        var name1 = getIntent().getStringExtra("name")
        goActivity.setOnClickListener {
            var i = Intent(this@Description, AddStudentActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.putExtra("name", name1)
            startActivity(i)

        }
        listViewItems = findViewById<View>(R.id.liststudent) as ListView
        toDoStudentList = mutableListOf<AStudent>()
        adapter = ToDoStudentAdapter(this@Description, toDoStudentList!!)
        listViewItems!!.setAdapter(adapter)

        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.child("AddStudent").addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.iterator()

                var nameevent = getIntent().getStringExtra("name") //รับมา

                if (items.hasNext()) {
                    while (items.hasNext()) {
                        val toDoListindex = items.next()
                        val map = toDoListindex.getValue() as HashMap<String, Any>

                        if (map.get("newName") == name) {
                            // add data to object
                            val todoItem = AStudent.create()
                            todoItem.NameStudent = map.get("nameStudent") as String?
                            todoItem.IdStudent = map.get("idStudent") as String?
                            toDoStudentList!!.add(todoItem);
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }
}








