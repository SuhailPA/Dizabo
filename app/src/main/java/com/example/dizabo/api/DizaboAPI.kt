package com.example.dizabo.api

import com.example.dizabo.data.LoginRequestBody
import com.example.dizabo.data.LoginResponseBody
import com.example.dizabo.data.getalldata.HomeResponseBody
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DizaboAPI {

    companion object {
        const val BASE_URL = "http://3.28.48.179:4000/v1/api/"
    }

    @POST("app/investor/login")
    suspend fun loginUser(@Body loginRequestBody: LoginRequestBody): Response<LoginResponseBody>

    @GET("app/investment_plan/get_all")
    suspend fun getAllData(@Header("Authorization")token : String) : Response<HomeResponseBody>


}