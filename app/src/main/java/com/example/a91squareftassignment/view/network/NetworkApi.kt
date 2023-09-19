package com.example.a91squareftassignment.view.network

import com.example.a91squareftassignment.model.DataModel
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET("/webhook/223a7fe0-4e32-4414-aacf-1bc0ab1c0bba")
     suspend fun getData():Response<DataModel>

}