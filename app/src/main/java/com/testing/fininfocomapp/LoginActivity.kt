package com.testing.fininfocomapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        auth = FirebaseAuth.getInstance()

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (isValidUsername(username) && isValidPassword(password)) {
                if (username.equals("Fininfocom") && password.equals("Fin@123")){

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this,"Credentials incorrect",Toast.LENGTH_SHORT).show()
                }
             //   signInWithUsernamePassword(username, password)
            } else {
                // Show validation error messages
                if (!isValidUsername(username)) {
                    usernameEditText.error = "Invalid username"
                }
                if (!isValidPassword(password)) {
                    passwordEditText.error = "Invalid password"
                }
            }
        }
    }

        fun isValidUsername(username: String): Boolean {
            return username.length == 10
        }

        fun isValidPassword(password: String): Boolean {
            val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=]).{7,}\$"
            return password.matches(passwordRegex.toRegex())
        }


/*    private fun signInWithUsernamePassword(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Credentials incorrect",Toast.LENGTH_SHORT).show()
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidUserException) {
                        // Handle invalid user
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        // Handle invalid credentials
                    } catch (e: Exception) {
                        // Handle other exceptions
                    }
                }
            }
    }*/
}