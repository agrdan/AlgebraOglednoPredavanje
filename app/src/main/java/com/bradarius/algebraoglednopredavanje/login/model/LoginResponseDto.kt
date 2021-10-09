package com.bradarius.algebraoglednopredavanje.login.model

import com.bradarius.algebraoglednopredavanje.presentation.model.User

data class LoginResponseDto(
    val message: String?,
    val code: Int,
    val user: User?,
    val token: String?
)
