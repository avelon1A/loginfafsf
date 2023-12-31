package com.example.login.data


import com.example.login.data.data_source.LoginCredentials
import com.example.login.data.data_source.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
 fun login(@Body credentials: LoginCredentials): Call<LoginResponse>


    }
