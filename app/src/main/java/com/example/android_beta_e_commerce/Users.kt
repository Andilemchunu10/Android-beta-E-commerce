package com.example.android_beta_e_commerce
import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

data class User(val username: String, val password: String, val email: String?)

fun readJsonFile(context: Context, fileName: String): String? {
    return try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charset.forName("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
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
