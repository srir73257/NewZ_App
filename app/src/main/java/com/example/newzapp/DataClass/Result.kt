package com.example.newzapp.DataClass

data class Result(
    val article_id: String,
    val country: List<String>,
    val description: String,
    val image_url: String,
    val language: String,
    val link: String,
    val pubDate: String,
    val source_icon: String,
    val source_name: String,
    val source_url: String,
    val title: String,
)
