package com.example.firebasebasic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var firebaseDatabase = FirebaseDatabase.getInstance()
    private var databaseReference = firebaseDatabase.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        signUpButton.setOnClickListener {
            if (validate()) {
                //creating user after validation
                firebaseAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            var user = User(etEmail.text.toString(), etPassword.text.toString())
                            databaseReference.child(firebaseAuth.currentUser?.uid!!)
                                .setValue(user)
                                .addOnCompleteListener {
                                    val intent = Intent(baseContext, DisplayActivity::class.java)
                                    startActivity(intent)
                                }

                        }
                    }
            }
        }

    }

    //validating user email and password
    private fun validate(): Boolean {
        var isValid = true
        //valid email
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            etEmail.error = "Invalid Email"
            isValid = false
        }
        //valid password
        if (etPassword.text.toString().length < 8) {
            etPassword.error = "Password is too short"
            isValid = false
        }
        return isValid
    }
}