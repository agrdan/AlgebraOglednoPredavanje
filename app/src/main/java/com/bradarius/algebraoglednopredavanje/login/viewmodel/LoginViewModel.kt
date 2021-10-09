package com.bradarius.algebraoglednopredavanje.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bradarius.algebraoglednopredavanje.login.model.LoginDto
import com.bradarius.algebraoglednopredavanje.login.model.LoginResponseDto
import com.bradarius.algebraoglednopredavanje.repository.LoginRepository

class LoginViewModel(private val repo: LoginRepository): ViewModel() {

    val loginData: LiveData<LoginResponseDto> = repo.loginResponse
    val loginDtoData: LiveData<LoginDto> = repo.loginInfo

    fun login(loginDto: LoginDto) {
        repo.login(loginDto)
    }

    fun clearLogin() {
        repo.clearLogin()
    }
}