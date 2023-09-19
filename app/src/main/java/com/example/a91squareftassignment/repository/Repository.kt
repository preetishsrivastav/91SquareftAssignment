package com.example.a91squareftassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a91squareftassignment.model.Data
import com.example.a91squareftassignment.model.DataModel
import com.example.a91squareftassignment.view.network.NetworkApi

class Repository(private val networkApi: NetworkApi) {
private val fileMutableLiveData = MutableLiveData<List<Data>>()

    val fileLiveData :LiveData<List<Data>>
        get() = fileMutableLiveData


    suspend fun getData(){
      val result =  networkApi.getData()
        val dataList = result.body()?.data
       if (result.body() != null){
           fileMutableLiveData.postValue(dataList)
       }

    }
}