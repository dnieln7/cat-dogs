package com.dnieln7.catdogs.utils

sealed class ApiState {
    object Loading : ApiState()
    object Success : ApiState()
    class Error(val error: String) : ApiState()
}
