package com.bradarius.trainingplanner.api.login

import com.bradarius.algebraoglednopredavanje.login.model.LoginDto
import com.bradarius.algebraoglednopredavanje.login.model.LoginResponseDto
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginInterface {
    companion object {
        private const val PREFIX = "/login-service/v1"

    }

    @POST("$PREFIX/login")
    fun login(@Body loginDto: LoginDto) : Call<LoginResponseDto>

    @POST("$PREFIX/registration")
    fun registration(@Body loginDto: LoginDto) : Call<LoginResponseDto>

}