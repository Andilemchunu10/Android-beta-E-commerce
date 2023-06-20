package com.example.android_beta_e_commerce

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username: EditText = findViewById(R.id.usernameTxt)
        val password: EditText = findViewById(R.id.passwordTxt)
        val registerBtn: Button = findViewById(R.id.registerBtn)
        val userEmail: EditText = findViewById(R.id.emailTxt)
        val skipT: TextView = findViewById(R.id.skiptxt)
        val confirmPassword: EditText = findViewById(R.id.confirmPasswordTxt)
        val confirmWarning: TextView = findViewById(R.id.confirmPAsswordWarning)
        val passwordWarning: TextView = findViewById(R.id.passwordWarning)
        val emailWarning: TextView = findViewById(R.id.emailWarning)
        val usernameWarning: TextView = findViewById(R.id.usernameWarning)
        val registerTextview: TextView = findViewById(R.id.registerTextview)

        registerTextview.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        registerBtn.setOnClickListener {
            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()
            val enteredEmail = userEmail.text.toString()
            val enteredConfirmationPassword = confirmPassword.text.toString()

            if (!isUsernameFormatValid(enteredUsername)) {
                // Username is not in email format
                usernameWarning.text = "Invalid, Username should be the same as the email"
                return@setOnClickListener
            } else if (!isPasswordValid(enteredPassword)) {
                // Password is not valid
                passwordWarning.text =
                    "Password should contain at least 8 characters and at least one uppercase letter, lowercase letter, and digit"
                return@setOnClickListener
            } else if (enteredPassword != enteredConfirmationPassword) {
                // Passwords don't match
                confirmWarning.text = "Passwords must match"
                return@setOnClickListener
            } else if (!isEmailFormatValid(enteredEmail)) {
                // Invalid email
                emailWarning.text = "Invalid email format, please enter a valid email"
                return@setOnClickListener
            }

            val newUser = User(enteredUsername, enteredPassword, enteredEmail)
            val users = readUsersFromJson().toMutableList()
            users.add(newUser)

            saveUsersToJsonFile(users)

            Snackbar.make(it, "User Registered Successfully", Snackbar.LENGTH_SHORT).show()


        }

        skipT.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun readUsersFromJson(): List<User> {
        val jsonFileName = "Users.json"
        val jsonFile = File(filesDir, jsonFileName)

        if (!jsonFile.exists()) {
            return emptyList()
        }

        val jsonString = jsonFile.readText()
        val gson = Gson()
        val usersType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(jsonString, usersType)
    }

    private fun saveUsersToJsonFile(users: List<User>) {
        val jsonFileName = "Users.json"
        val jsonFile = File(filesDir, jsonFileName)

        try {
            val gson = Gson()
            val jsonString = gson.toJson(users)
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            jsonFile.writeText(jsonString)
        } catch (e: IOException) {
            // Handle the exception
        }
    }


    private fun isEmailFormatValid(enteredEmail: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()
    }

    private fun isUsernameFormatValid(enteredUsername: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(enteredUsername).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$".toRegex()
        return password.matches(passwordPattern)
    }
}
