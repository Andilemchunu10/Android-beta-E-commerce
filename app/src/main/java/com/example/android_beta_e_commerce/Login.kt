package com.example.android_beta_e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val skipTxt: TextView = findViewById(R.id.skipTxt)
        val username: EditText = findViewById(R.id.usernameTxt)
        val password: EditText = findViewById(R.id.passwordTxt)
        val loginbtn: Button = findViewById(R.id.registerBtn)
        val registerTextV: TextView = findViewById(R.id.registerTextview)


        skipTxt?.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        registerTextV.setOnClickListener {
            val intent = Intent (this, Register::class.java)
            startActivity(intent)
        }

        loginbtn.setOnClickListener {
            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()



            if (checkUserExists(enteredUsername, enteredPassword)) {
                // User exists
                // Proceed with login or navigate to the next screen
                val intent = Intent (this, Home::class.java)
                startActivity(intent)
                Snackbar.make(it, "User Logged In Successfully", Snackbar.LENGTH_SHORT).show()
            } else {
                // User does not exist
                Snackbar.make(it, "Invalid Username or Password", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

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


}