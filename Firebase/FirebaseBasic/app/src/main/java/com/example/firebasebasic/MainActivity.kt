package com.example.firebasebasic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Write a message to the database
    private val database = Firebase.database
    private val myRef = database.getReference("parent")
        .child("child_1")
        .child("child_2")

        

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val s = Students("mausam", 20)

        val ar:ArrayList<String> = ArrayList()
        for(i in 1..10) ar.add("$i mausam")

        myRef.setValue(s)


        //Realtime Database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val genericTypeIndicator: GenericTypeIndicator<Students?> =
                    object : GenericTypeIndicator<Students?>() {}
//                val genericTypeIndicator: GenericTypeIndicator<ArrayList<String>?> =
//                    object : GenericTypeIndicator<ArrayList<String>?>() {}
                val s1 = snapshot.getValue(genericTypeIndicator)
                tv.text = s1!!.name + " " + s1.roll
                //tv.text = "${s1!!.name} ${s1!!.roll}"
                //tv.text = ar[0]
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })



        //Firebase Authentication
        //sign in and signup using firebase auth
        //sign in
        btnSignIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        //sign up
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}
