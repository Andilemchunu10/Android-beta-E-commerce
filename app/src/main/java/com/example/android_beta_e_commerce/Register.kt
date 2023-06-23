package com.example.android_beta_e_commerce

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Handler
import android.text.TextUtils.isEmpty
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.ImageView

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Assuming you have a color defined in your resources with the name "red"
        val redColorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))

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

        val showPassword: ImageView = findViewById(R.id.showPasswordIcon)
        val showConfirmationPassword: ImageView= findViewById(R.id.showPasswordIcon)



        password.transformationMethod = PasswordTransformationMethod.getInstance()
        showPassword.setImageResource(R.drawable.hide_password)

        showPassword.setOnClickListener {
            val isVisible = password.transformationMethod is PasswordTransformationMethod

            if (isVisible) {
                // Show password
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showPassword.setImageResource(R.drawable.show_password_icon)
            } else {
                // Hide password
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                showPassword.setImageResource(R.drawable.hide_password)
            }

            // Move cursor to the end of the text
            password.setSelection(password.text.length)
        }

//        confirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
//        showConfirmationPassword.setImageResource(R.drawable.hide_password)
//
//        showConfirmationPassword.setOnClickListener {
//            val isVisible = confirmPassword.transformationMethod is PasswordTransformationMethod
//
//            if (isVisible) {
//                // Show password
//                confirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
//                showConfirmationPassword.setImageResource(R.drawable.show_password_icon)
//            } else {
//                // Hide password
//                confirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
//                showConfirmationPassword.setImageResource(R.drawable.hide_password)
//            }
//
//            // Move cursor to the end of the text
//            confirmPassword.setSelection(confirmPassword.text.length)
//        }


        registerTextview.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        registerBtn.setOnClickListener {
            // Clear the warning TextViews
            usernameWarning.text = ""
            passwordWarning.text = ""
            confirmWarning.text = ""
            emailWarning.text = ""

            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()
            val enteredEmail = userEmail.text.toString()
            val enteredConfirmationPassword = confirmPassword.text.toString()

            val defaultTint = username.backgroundTintList // Store the default background tint

            username.backgroundTintList = defaultTint
            password.backgroundTintList = defaultTint
            confirmPassword.backgroundTintList = defaultTint
            userEmail.backgroundTintList = defaultTint

            if ((isEmpty(enteredUsername)) && (isEmpty(enteredPassword)) &&
                (isEmpty(enteredPassword)) &&  (isEmpty(enteredEmail))) {
                usernameWarning.text = "Please enter username (email)"
                username.backgroundTintList = redColorStateList

                passwordWarning.text =
                    "Please enter Password"
                password.backgroundTintList = redColorStateList

                confirmWarning.text = "Please enter confirmation password"
                confirmPassword.backgroundTintList = redColorStateList

                emailWarning.text = "Please enter Email"
                userEmail.backgroundTintList = redColorStateList

                return@setOnClickListener
            } else if ((isEmpty(enteredPassword)) &&
                (isEmpty(enteredPassword)) &&  (isEmpty(enteredEmail))) {

                passwordWarning.text =
                    "* Please enter Password"
                password.backgroundTintList = redColorStateList

                confirmWarning.text = "* Please enter confirmation password"
                confirmPassword.backgroundTintList = redColorStateList

                emailWarning.text = "* Please enter Email"
                userEmail.backgroundTintList = redColorStateList

                return@setOnClickListener
            } else if ((isEmpty(enteredPassword)) &&  (isEmpty(enteredEmail))) {

                confirmWarning.text = "* Please enter confirmation password"
                confirmPassword.backgroundTintList = redColorStateList

                emailWarning.text = "* Please enter Email"
                userEmail.backgroundTintList = redColorStateList

                return@setOnClickListener
            } else if ((isEmpty(enteredEmail))) {

                emailWarning.text = "* Please enter Email"
                userEmail.backgroundTintList = redColorStateList

                return@setOnClickListener
            } else if (!isUsernameFormatValid(enteredUsername)) {
                // Username is not in email format
                usernameWarning.text = "* Invalid, Username should be the same as the email"
                username.backgroundTintList = redColorStateList
                return@setOnClickListener
            } else if (!isPasswordValid(enteredPassword)) {
                // Password is not valid
                passwordWarning.text =
                    "* Password should contain at least 8 characters, one uppercase,one lowercase, digit"
                password.backgroundTintList = redColorStateList
                return@setOnClickListener
            } else if (enteredPassword != enteredConfirmationPassword) {
                // Passwords don't match
                confirmWarning.text = "* Passwords must match"
                confirmPassword.backgroundTintList = redColorStateList
                return@setOnClickListener
            } else if (!isEmailFormatValid(enteredEmail)) {
                // Invalid email
                emailWarning.text = "* Invalid email format, please put a correct format"
                userEmail.backgroundTintList = redColorStateList
                return@setOnClickListener
            }

            // Reset the background tint to the default state
            username.backgroundTintList = defaultTint
            password.backgroundTintList = defaultTint
            confirmPassword.backgroundTintList = defaultTint
            userEmail.backgroundTintList = defaultTint

            val newUser = IndividualUser(enteredUsername, enteredPassword, enteredEmail)
            val users = readUsersFromJson(this).toMutableList()
            users.add(newUser)

            saveUsersToJsonFile(this, users)

            Snackbar.make(it, "User Registered Successfully", Snackbar.LENGTH_SHORT).show()
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }, 2500)
        }

        skipT.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun readUsersFromJson(context: Context): List<IndividualUser> {
        val jsonFileName = "Users.json"
        val jsonFile = File(context.filesDir, jsonFileName)

        if (!jsonFile.exists()) {
            return emptyList()
        }

        val jsonString = jsonFile.readText()
        val gson = Gson()
        val usersType = object : TypeToken<List<IndividualUser>>() {}.type
        return gson.fromJson(jsonString, usersType)
    }

    private fun saveUsersToJsonFile(context: Context, users: List<IndividualUser>) {
        val jsonFileName = "Users.json"
        val jsonFile = File(context.filesDir, jsonFileName)

        try {
            val gson = Gson()
            val jsonString = gson.toJson(users)
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
