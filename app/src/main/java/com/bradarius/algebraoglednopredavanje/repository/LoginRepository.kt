package com.bradarius.algebraoglednopredavanje.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bradarius.algebraoglednopredavanje.BuildConfig
import com.bradarius.algebraoglednopredavanje.login.model.LoginDto
import com.bradarius.algebraoglednopredavanje.login.model.LoginResponseDto
import com.bradarius.trainingplanner.api.RetrofitInstance
import com.bradarius.trainingplanner.api.login.LoginInterface
import retrofit2.Call
import retrofit2.Callback

class LoginRepository {

    companion object {
        const val TAG = "_LoginRepository"
    }


    private val retrofitInstance =  RetrofitInstance(BuildConfig.BASE_URL, null)
        .buildService(LoginInterface::class.java)

    val loginResponse = MutableLiveData<LoginResponseDto>()
    val loginInfo = MutableLiveData<LoginDto>()


    fun login(loginDto: LoginDto) {

        loginInfo.value = loginDto
        val call = retrofitInstance.login(loginDto)

        call.enqueue(object : Callback<LoginResponseDto> {
            override fun onResponse(
                call: Call<LoginResponseDto>,
                response: retrofit2.Response<LoginResponseDto>
            ) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body()
                }
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                val msg = t.localizedMessage
                if (msg != null) Log.d(TAG, msg)
                else Log.d(TAG, "Error occuried but message is null")
            }

        })
    }

    fun clearLogin() {
        loginResponse.value = LoginResponseDto(null, 0, null, null)
    }
}