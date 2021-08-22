package com.dnieln7.catdogs.ui.cat

import android.util.Log
import androidx.lifecycle.*
import com.dnieln7.catdogs.data.source.cat.CatRemoteDataSource
import com.dnieln7.catdogs.domain.cat.Cat
import com.dnieln7.catdogs.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatsViewModel(private val catRemoteDataSource: CatRemoteDataSource) : ViewModel() {
    private val _apiState = MutableLiveData<ApiState>()
    private val _cats = MutableLiveData<List<Cat>>()

    val cats: LiveData<List<Cat>> = _cats
    val apiState: LiveData<ApiState> = _apiState

    init {
        fetchCats()
    }

    fun fetchCats() {
        _apiState.value = ApiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = catRemoteDataSource.getCats(30)

                withContext(Dispatchers.Main) {
                    _cats.value = result
                    _apiState.value = ApiState.Success
                }
            } catch (e: Exception) {
                Log.e(CatsViewModel::class.simpleName, "Error", e)
                withContext(Dispatchers.Main) {
                    _apiState.value = ApiState.Error("No connection")
                }
            }
        }
    }

    class Factory(private val catRemoteDataSource: CatRemoteDataSource) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatsViewModel::class.java)) {
                return CatsViewModel(catRemoteDataSource) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}