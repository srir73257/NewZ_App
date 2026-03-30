package com.example.newzapp.ui.theme

sealed class State_d {

    object Success : State_d()
    object loading : State_d()
    data class Error(val message: String): State_d()
}