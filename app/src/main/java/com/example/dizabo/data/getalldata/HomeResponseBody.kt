package com.example.dizabo.data.getalldata

data class HomeResponseBody(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)