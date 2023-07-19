package com.example.dizabo.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dizabo.data.LoginRequestBody
import com.example.dizabo.data.LoginResponseBody
import com.example.dizabo.repository.DizaboRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: DizaboRepository,
    private val pref: SharedPreferences
) : ViewModel() {

    fun loginUser(loginRequestBody: LoginRequestBody): Flow<Result<LoginResponseBody>> = flow {
        val response: Response<LoginResponseBody> = repository.loginUser(loginRequestBody)

        if (response.isSuccessful) {
            val loginResponse = response.body()

            if (loginResponse?.message == "success") {
                storeTokenInPref(loginResponse)
                emit(Result.success(loginResponse))
            } else emit(Result.failure(Exception("Login Failed")))

        }
    }

    /**
     * Stored the token in encryptedSharedPref
     */
    private fun storeTokenInPref(response: LoginResponseBody) {
        viewModelScope.launch(Dispatchers.IO) {
            pref.edit().putString("userToken", response.data.token).apply()
        }

    }
}