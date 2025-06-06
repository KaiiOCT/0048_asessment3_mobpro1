package com.faris0048.asessment3.network

import com.faris0048.asessment3.model.BangunRuang
import com.faris0048.asessment3.model.OpStatus
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


private const val BASE_URL = "https://mobpro-api-production.up.railway.app/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BangunRuangApiService {
    @GET("bangun-ruang")
    suspend fun getBangunRuang(
        @Header("Authorization") userId: String
    ): List<BangunRuang>

    @Multipart
    @POST("bangun-ruang/store")
    suspend fun postBangunRuang(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part gambar: MultipartBody.Part
    ): OpStatus

    @Multipart
    @POST("bangun-ruang/edit/{id}")
    suspend fun editBangunRuang(
        @Header("Authorization") userId: String,
        @Path("id") id: String,
        @Part("nama") nama: RequestBody,
        @Part gambar: MultipartBody.Part
    ): OpStatus

    @DELETE("bangun-ruang/delete/{id}")
    suspend fun deleteBangunRuang(
        @Header("Authorization") userId: String,
        @Path("id") id: String
    ): OpStatus
}

object BangunRuangApi {
    val service: BangunRuangApiService by lazy {
        retrofit.create(BangunRuangApiService::class.java)
    }

    fun getBangunRuang(url: String): String {
        return "https://mobpro-api-production.up.railway.app/storage/$url"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }
