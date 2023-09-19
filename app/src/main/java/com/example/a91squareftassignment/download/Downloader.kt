package com.example.a91squareftassignment.download

interface Downloader {

    fun downloadFile(url: String, mimeType:String,title:String): Long

}