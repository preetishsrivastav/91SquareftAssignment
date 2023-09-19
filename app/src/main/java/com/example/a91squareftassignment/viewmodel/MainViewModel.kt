package com.example.a91squareftassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a91squareftassignment.model.Data
import com.example.a91squareftassignment.model.DataModel
import com.example.a91squareftassignment.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            val result=  repository.getData()
      }
    }

    val fileLiveData:LiveData<List<Data>>
        get() = repository.fileLiveData
}