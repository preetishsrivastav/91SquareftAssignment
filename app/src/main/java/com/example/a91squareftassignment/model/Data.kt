package com.example.a91squareftassignment.model

data class Data(
    val comment_count: Int,
    val `file`: String,
    val file_size: Double,
    val id: String,
    val name: String,
    val section: String,
    val status: Int,
    val tags: List<Tag>,
    val type: String,
    val uploaded_at: String,
    val uploaded_by: UploadedBy,
    val version: Int
)