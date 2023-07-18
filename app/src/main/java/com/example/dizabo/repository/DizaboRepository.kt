package com.example.dizabo.repository

import com.example.dizabo.api.DizaboAPI
import com.example.dizabo.data.LoginRequestBody
import javax.inject.Inject

class DizaboRepository @Inject constructor(private val dizaboAPI: DizaboAPI) {

    suspend fun loginUser(loginRequestBody: LoginRequestBody) =
        dizaboAPI.loginUser(loginRequestBody)

}