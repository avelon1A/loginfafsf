package com.example.login

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import com.example.login.model.AuthViewModel
import com.example.login.model.SecondActivity
import com.example.myapplication.R






class LoginActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, AuthViewModelFactory(applicationContext))
            .get(AuthViewModel::class.java)


        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)



        val textView: TextView = findViewById(R.id.versionno)

//  this is use to display the version name on the login screen
        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
      textView.text ="version name ${info.versionName} version number ${info.versionCode } "

        // login button functionality
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.login(username, password)
        }


        viewModel.loginResult.observe(this) { result ->
            val name = result.first
            val image = result.second
            if (name==true) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent);

            } else {
                showToast("Login failed")
            }
           }


    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

