package com.example.android_beta_e_commerce

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username: EditText = findViewById(R.id.usernameTxt)
        val password: EditText = findViewById(R.id.passwordTxt)
        val registerbtn: Button = findViewById(R.id.registerBtn)

        registerbtn.setOnClickListener {
            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()

            if (checkUserExists(enteredUsername, enteredPassword)) {
                // User exists
                Toast.makeText(this, "User already exists.", Toast.LENGTH_SHORT).show()
            } else {
                // User does not exist
                // Add code to register the new user to the JSON file
            }
        }
    }

    // Function to read JSON file
    private fun readFile(fileName: String): String {
        return try {
            val inputStream = assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.defaultCharset())
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    // Function to check if user exists
    private fun checkUserExists(username: String, password: String): Boolean {
        val jsonStr = readFile("Users.json")
        val jsonObject = JSONObject(jsonStr)
        val usersArray = jsonObject.getJSONArray("users")

        for (i in 0 until usersArray.length()) {
            val userObject = usersArray.getJSONObject(i)
            val existingUsername = userObject.getString("username")
            val existingPassword = userObject.getString("password")

            if (existingUsername == username && existingPassword == password) {
                return true // User exists
            }
        }

        return false // User does not exist
    }
}
