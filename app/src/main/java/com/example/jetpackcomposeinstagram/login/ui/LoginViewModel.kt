package com.example.jetpackcomposeinstagram.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    //Este LiveData solo se va a poder modificar dentro del ViewModel
    private val _email = MutableLiveData<String>()

    //Este LiveData es el que tendrá acceso la vista, que podra leer, pero no modificar
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isEnable = MutableLiveData<Boolean>()
    val isEnable: LiveData<Boolean> = _isEnable
    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isEnable.value = enableButtonLogin(email, password)
    }

    fun enableButtonLogin(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }
}