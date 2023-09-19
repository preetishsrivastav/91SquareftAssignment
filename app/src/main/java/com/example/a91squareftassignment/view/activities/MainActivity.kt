package com.example.a91squareftassignment.view.activities

import android.Manifest
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a91squareftassignment.R
import com.example.a91squareftassignment.databinding.ActivityMainBinding
import com.example.a91squareftassignment.download.AndroidDownloader
import com.example.a91squareftassignment.repository.Repository
import com.example.a91squareftassignment.view.adapters.MainAdapter
import com.example.a91squareftassignment.view.network.RetrofitInstance
import com.example.a91squareftassignment.viewmodel.MainViewModel
import com.example.a91squareftassignment.viewmodel.ViewModelFactory
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.DexterBuilder.MultiPermissionListener
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var lm: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()


        val api = RetrofitInstance.api
        val repository = Repository(api)
        mainViewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.fileLiveData.observe(this, Observer {
            mainAdapter.dataList = it
        })


    }

    fun setUpRecyclerView() = binding.rvMain.apply {
        mainAdapter = MainAdapter(this@MainActivity,packageName)
        adapter = mainAdapter
        lm = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        layoutManager = lm
    }

}