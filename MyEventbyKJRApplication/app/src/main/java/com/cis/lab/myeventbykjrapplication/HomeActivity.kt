package com.cis.lab.myeventbykjrapplication
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.cis.lab.myeventbykjrapplication.Adapter.ToDoItemAdapter
import com.cis.lab.myeventbykjrapplication.model.ToDo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_result.*


class HomeActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    var toDoItemList: MutableList<ToDo>? = null
    lateinit var adapter: ToDoItemAdapter
    private var listViewItems: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        mDatabase = FirebaseDatabase.getInstance().reference
        listViewItems = findViewById<View>(R.id.listdata) as ListView

        toDoItemList = mutableListOf<ToDo>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)

        listdata.setOnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, Description::class.java)
            val selectedItem = parent.getItemAtPosition(position) as ToDo
            intent.putExtra("name",selectedItem.name.toString())
            intent.putExtra("date",selectedItem.date.toString())
            intent.putExtra("event",selectedItem.event.toString())
            intent.putExtra("num",selectedItem.num.toString())
            startActivity(intent)
        }
    }

    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("Student"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }

    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()
        var email = getIntent().getStringExtra("email")
        // Check if current database contains any collection
        if (items.hasNext()) {


            // check if the collection has any to do items or not
            while (items.hasNext()) {
                // get current item
                val currentItem = items.next()
                val map = currentItem.getValue() as HashMap<String, Any>
                // add data to object
                if (map.get("email") == email){
                    val todoItem = ToDo.create()
                    todoItem.name = map.get("name") as String
                    todoItem.date = map.get("date") as String
                    todoItem.event = map.get("event") as String
                    todoItem.num = map.get("num") as String
                    toDoItemList!!.add(todoItem);
                }
            }

            adapter.notifyDataSetChanged()
        }
    }
}
