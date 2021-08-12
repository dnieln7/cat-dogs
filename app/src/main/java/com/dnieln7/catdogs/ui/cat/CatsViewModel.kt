package com.dnieln7.catdogs.ui.cat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnieln7.catdogs.domain.cat.Breed
import com.dnieln7.catdogs.network.CatsApi
import com.dnieln7.catdogs.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatsViewModel : ViewModel() {
    private val _apiState = MutableLiveData<ApiState>()
    private val _cats = MutableLiveData<List<Breed>>()

    val cats: LiveData<List<Breed>> = _cats
    val apiState: LiveData<ApiState> = _apiState

    init {
        fetchCats()
    }

    fun fetchCats() {
        _apiState.value = ApiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = CatsApi.service.getBreeds(30)

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
}