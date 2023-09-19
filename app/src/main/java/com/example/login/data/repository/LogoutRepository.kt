package com.example.login.data.repository


import android.content.Context
import android.util.Log
import java.io.File

class LogoutRepository(private val context: Context) {

    // ... Other methods ...

    private fun clearCachedFiles() {
        // Implement logic to clear any cached files related to the user.
        val cacheDir: File? = context.cacheDir
        if (cacheDir != null) {
            val files: Array<File> = cacheDir.listFiles() ?: return

            for (file in files) {
                file.delete()
            }
        }
    }

    // Perform the complete logout operation.
    fun logout() {
        clearUserData()
        clearCachedFiles()
        Log.d("TAG", "logut")

        // Call other methods to clear the user session and notify the server as needed.
    }

    private fun clearUserData() {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        Log.d("TAG", "data clear")

    }
}
