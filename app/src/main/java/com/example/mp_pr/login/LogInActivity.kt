package com.example.mp_pr.login;

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mp_pr.MainActivity
import com.example.mp_pr.R
import com.example.mp_pr.SignUp.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LogInActivity : AppCompatActivity() {


    lateinit var logInEmail: EditText
    lateinit var logInPW : EditText
    lateinit var LogInBtn : Button
    lateinit var SignUpButton : Button
    private lateinit var auth : FirebaseAuth
    var db = Firebase.firestore

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = Firebase.auth
        logInEmail = findViewById<EditText>(R.id.edittext_id)
        logInEmail.hint = "이메일"
        logInPW = findViewById<EditText>(R.id.edittext_password)
        logInPW.hint = "비밀번호"
        LogInBtn = findViewById<Button>(R.id.SignInButton)
        SignUpButton = findViewById<Button>(R.id.SignUpButton)

        SignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        LogInBtn.setOnClickListener {
            createAccount(logInEmail.text.toString(), logInPW.text.toString())
        }



    }


    private fun createAccount(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful) {
                        Toast.makeText(
                            this, "로그인 성공",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth.currentUser)
                    } else {
                        Toast.makeText(
                            this, "로그인 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
        }
    }

    fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

