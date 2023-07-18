package com.example.dizabo.api

import com.example.dizabo.data.LoginRequestBody
import com.example.dizabo.data.LoginResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DizaboAPI {

    companion object {
        const val BASE_URL = "http://3.28.48.179:4000/v1/api/"
    }

    @POST("app/investor/login")
    suspend fun loginUser(@Body loginRequestBody: LoginRequestBody): Response<LoginResponseBody>
}