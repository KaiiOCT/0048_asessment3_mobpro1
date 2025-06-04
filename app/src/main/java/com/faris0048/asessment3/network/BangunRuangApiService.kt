package com.faris0048.asessment3.network

import com.faris0048.asessment3.model.BangunRuang
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://mobpro-api-production.up.railway.app/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BangunRuangApiService {
    @GET("bangun-ruang")
    suspend fun getBangunRuang(): List<BangunRuang>
}

object BangunRuangApi {
    val service: BangunRuangApiService by lazy {
        retrofit.create(BangunRuangApiService::class.java)
    }

    fun getBangunRuang(url: String): String {
        return "${BASE_URL}storage/$url"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }
