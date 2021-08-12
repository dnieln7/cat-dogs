package com.dnieln7.catdogs.network

import com.dnieln7.catdogs.domain.cat.Breed
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.thecatapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatsApiService {

    @GET("breeds")
    suspend fun getBreeds(@Query("limit") limit: Int): List<Breed>
}

object CatsApi {
    val service: CatsApiService by lazy {
        retrofit.create(CatsApiService::class.java)
    }
}