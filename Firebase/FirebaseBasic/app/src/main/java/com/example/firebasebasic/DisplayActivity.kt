package com.example.firebasebasic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_display.*

class DisplayActivity : AppCompatActivity() {
    
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var firebaseDatabase = FirebaseDatabase.getInstance()
    private var databaseReference = firebaseDatabase.getReference("users")

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser!=null) {
            
            val genericTypeIndicator: GenericTypeIndicator<User?> = object : GenericTypeIndicator<User?>() {}
            
            databaseReference.child(firebaseAuth.currentUser!!.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(genericTypeIndicator)
                    tvName.text = if (user != null) user.name else throw NullPointerException("Expression 'user' must not be null")
                    tvEMail.text = user.email
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        } else {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
    }
}