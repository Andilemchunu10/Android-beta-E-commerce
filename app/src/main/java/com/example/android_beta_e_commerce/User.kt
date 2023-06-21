package com.example.android_beta_e_commerce
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.IOException

class User(
    val username: String,
    val password: String,
    val email: String
)

// ...

private fun readUsersFromJson(context: Context): List<User> {
    val jsonFileName = "Users.json"
    val jsonFile = File(context.filesDir, jsonFileName)

    if (!jsonFile.exists()) {
        return emptyList()
    }

    val gson = Gson()
    val jsonReader = JsonReader(jsonFile.reader())
    val usersType = object : TypeToken<Map<String, List<User>>>() {}.type
    val usersMap: Map<String, List<User>> = gson.fromJson(jsonReader, usersType)
    return usersMap["users"] ?: emptyList()
}

private fun saveUsersToJsonFile(context: Context, users: List<User>) {
    val jsonFileName = "Users.json"
    val jsonFile = File(context.filesDir, jsonFileName)

    val gson = GsonBuilder().setPrettyPrinting().create()
    val usersMap = mapOf("users" to users)

    try {
        val jsonString = gson.toJson(usersMap)
        jsonFile.writeText(jsonString)
    } catch (e: IOException) {
        // Handle the exception
    }
}


//fun main(context: Context) {
//    val jsonContent = readJsonFile(context, "Users.json")
//    if (jsonContent != null) {
//        val jsonObject = JSONObject(jsonContent)
//        val usersArray = jsonObject.getJSONArray("users")
//
//        val users = mutableListOf<User>()
//        for (i in 0 until usersArray.length()) {
//            val userObj = usersArray.getJSONObject(i)
//            val username = userObj.getString("username")
//            val password = userObj.getString("password")
//            val email = userObj.getString("email")
//            val user = User(username, password, email)
//            users.add(user)
//        }
//
//        // Use the 'users' list as per your application's requirements
//        for (user in users) {
//            println(user.username)
//            println(user.password)
//            println(user.email)
//        }
//    }
//}
