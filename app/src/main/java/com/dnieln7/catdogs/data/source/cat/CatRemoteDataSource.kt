package com.dnieln7.catdogs.data.source.cat

import com.dnieln7.catdogs.domain.cat.Cat

interface CatRemoteDataSource {

    suspend fun getCats(limit: Int): List<Cat>
}