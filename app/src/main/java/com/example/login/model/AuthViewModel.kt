package com.example.login.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.data.repository.AuthRepository
import com.example.login.data.repository.LogoutRepository

class AuthViewModel(private val applicationContext: Context) : ViewModel() {
    private val authRepository = AuthRepository()
    private val logoutRepository = LogoutRepository(applicationContext)

    private val _loginResult = MutableLiveData<Pair<Boolean, String?>>()
    val loginResult: LiveData<Pair<Boolean, String?>> = _loginResult

    fun login(username: String, password: String) {
        authRepository.login(username, password) { success, message ->
            _loginResult.value = Pair(success, message)
        }
    }

    fun logout() {
        logoutRepository.logout()
    }
}
