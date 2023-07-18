package com.example.dizabo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dizabo.data.LoginRequestBody
import com.example.dizabo.data.LoginResponseBody
import com.example.dizabo.repository.DizaboRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: DizaboRepository
) : ViewModel() {

    fun loginUser(loginRequestBody: LoginRequestBody) : Flow<Result<LoginResponseBody>> = flow {
        val response : Response<LoginResponseBody> = repository.loginUser(loginRequestBody)

        if (response.isSuccessful) {
            val loginResponse = response.body()

            if (loginResponse?.message == "success") emit(Result.success(loginResponse))
            else emit(Result.failure(Exception("Login Failed")))

        }
    }
}