package com.example.login.data.repository

import com.example.login.data.AuthService
import com.example.login.data.data_source.LoginCredentials
import com.example.login.data.data_source.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AuthRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/") // Replace with your actual API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val authService = retrofit.create(AuthService::class.java)

    fun login(
        username: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {
        val credentials = LoginCredentials(username, password)
        authService.login(credentials).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val success = loginResponse?.image
                    callback.invoke(true, success)
                } else {
                    callback.invoke(false, null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                callback.invoke(false, null)
            }
        })
    }



}
