package com.dnieln7.catdogs

import android.content.Context
import com.dnieln7.catdogs.data.network.Api
import com.dnieln7.catdogs.data.source.cat.CatApiDataSource

class ServiceLocator(context: Context) {

    private val api = Api("https://api.thecatapi.com/v1/")

    val catRemoteDataSource = CatApiDataSource(api.catService)
}