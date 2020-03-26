package com.cis.lab.myeventbykjrapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private val TAG:String = "Result Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        button5.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("email",textView5.text.toString())
            startActivity(intent)
        }

        button7.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("email",textView5.text.toString())
            startActivity(intent)
        }

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser

        textView5.text = user!!.email
        //textView6.text = user!!.uid

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users =firebaseAuth.currentUser
            if (users == null){
                startActivity(Intent(this@ResultActivity,LoginActivity::class.java))
                finish()
            }
        }
        button.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"Signed Out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener { mAuthListener }
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null){ mAuth!!.removeAuthStateListener { mAuthListener }}
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){moveTaskToBack(true)}
        return super.onKeyDown(keyCode, event)


    }
}
