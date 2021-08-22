package com.dnieln7.catdogs.data.source.cat

import com.dnieln7.catdogs.data.network.cat.CatService
import com.dnieln7.catdogs.domain.cat.Cat

class CatApiDataSource(private val service: CatService) : CatRemoteDataSource {

    override suspend fun getCats(limit: Int): List<Cat> {
        return service.getCats(limit)
    }
}