package com.example.dizabo.repository

import android.content.SharedPreferences
import com.example.dizabo.api.DizaboAPI
import com.example.dizabo.data.LoginRequestBody
import com.example.dizabo.data.getalldata.HomeResponseBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class DizaboRepository @Inject constructor(
    private val dizaboAPI: DizaboAPI,
    private val pref: SharedPreferences
) {

    suspend fun loginUser(loginRequestBody: LoginRequestBody) =
        dizaboAPI.loginUser(loginRequestBody)

    suspend fun homeGetAllData(): Flow<Response<HomeResponseBody>> =
        flow { emit(dizaboAPI.getAllData(pref.getString("userToken", "").toString())) }

}