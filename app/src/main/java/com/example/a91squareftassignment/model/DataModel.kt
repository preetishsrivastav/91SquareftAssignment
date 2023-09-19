package com.example.a91squareftassignment.model

data class DataModel(
    val code: Int,
    val `data`: List<Data>,
    val errors: Any,
    val message: String,
    val success: Boolean
)