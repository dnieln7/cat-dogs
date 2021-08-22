package com.dnieln7.catdogs.data.network.cat

import com.dnieln7.catdogs.domain.cat.Breed
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("breeds")
    suspend fun getBreeds(@Query("limit") limit: Int): List<Breed>
}