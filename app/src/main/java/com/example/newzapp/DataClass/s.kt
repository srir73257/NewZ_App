package com.example.newzapp.DataClass

data class JsonConveter(
    val nextPage: String?,
    val results: List<Result>,
    val status: String
)