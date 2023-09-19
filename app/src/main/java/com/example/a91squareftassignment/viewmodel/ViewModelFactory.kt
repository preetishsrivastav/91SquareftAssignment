package com.example.a91squareftassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.a91squareftassignment.repository.Repository

class ViewModelFactory(val repository: Repository): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MainViewModel(repository) as T

    }

}