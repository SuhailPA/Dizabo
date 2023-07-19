package com.example.dizabo.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.dizabo.api.AuthInterceptor
import com.example.dizabo.api.DizaboAPI
import com.example.dizabo.api.DizaboAPI.Companion.BASE_URL
import com.example.dizabo.repository.DizaboRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DizaboModule {


    @Provides
    @Singleton
    fun providesHttpClient(pref: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(pref.getString("userToken", "").toString()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideDizaboAPI(retrofit: Retrofit): DizaboAPI = retrofit.create(DizaboAPI::class.java)

    @Provides
    @Singleton
    fun provideRepository(dizaboAPI: DizaboAPI, pref: SharedPreferences): DizaboRepository =
        DizaboRepository(dizaboAPI, pref)

    @Provides
    @Singleton
    fun getEncryptedSharedPref(application: Application): SharedPreferences {
        val masterAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "secured_prefs",
            masterAlias,
            application.applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

}