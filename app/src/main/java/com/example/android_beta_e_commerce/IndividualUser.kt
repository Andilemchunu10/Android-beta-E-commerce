package com.example.android_beta_e_commerce

import android.content.Context
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class IndividualUser(val username: String, val password: String, val email: String)


fun readJsonFile(context: Context, fileName: String): String? {
    try {
        val inputStream = context.openFileInput(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.defaultCharset())
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
    return null
}

fun writeJsonFile(context: Context, fileName: String, jsonContent: String) {
    try {
        val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        outputStream.write(jsonContent.toByteArray())
        outputStream.close()
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
}

fun userExists(users: List<User>, username: String): Boolean {
    return users.any { it.username == username }
}

fun registerUser(context: Context, user: User) {
    val jsonContent = readJsonFile(context, "users.json")
    if (jsonContent != null) {
        val usersArray = JSONArray(jsonContent)

        // Convert JSONArray to a mutable list of User objects
        val users = mutableListOf<User>()
        for (i in 0 until usersArray.length()) {
            val userObj = usersArray.getJSONObject(i)
            val username = userObj.getString("username")
            val password = userObj.getString("password")
            val email = userObj.getString("email")
            val existingUser = User(username, password, email)
            users.add(existingUser)
        }

        // Check if the user already exists
        if (userExists(users, user.username)) {
            // User already exists, handle accordingly
            // For example, show an error message to the user
        } else {
            // Add the new User object
            users.add(user)

            // Convert the list back to a JSONArray
            val updatedUsersArray = JSONArray()
            for (user in users) {
                val userObj = JSONObject()
                userObj.put("username", user.username)
                userObj.put("password", user.password)
                userObj.put("email", user.email)
                updatedUsersArray.put(userObj)
            }

            // Write the updated JSONArray back to the file
            writeJsonFile(context, "users.json", updatedUsersArray.toString())
        }
    }
}





