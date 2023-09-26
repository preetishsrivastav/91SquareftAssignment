package com.example.a91squareftassignment.view.adapters

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.media.audiofx.Equalizer.Settings
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a91squareftassignment.databinding.ItemLayoutBinding
import com.example.a91squareftassignment.download.AndroidDownloader
import com.example.a91squareftassignment.model.Data
import com.example.a91squareftassignment.model.DataModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlin.math.log

class MainAdapter(val context: Context,val pack:String ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val itemCallback = object : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, itemCallback)
    var dataList: List<Data>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val downloader = AndroidDownloader(context)


        holder.binding.apply {
            val data = dataList[position]
            name.text = data.name

            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(data.file)
            println(fileExtension)
            val mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension)
            println(mime)
            val title= data.type

            ibDownload.setOnClickListener {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                {
                    downloader.downloadFile(data.file,mime!!,title)
                }
                else {
                    Dexter.withContext(context)
                        .withPermissions(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ).withListener(object : MultiplePermissionsListener {
                            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                                if (report!!.areAllPermissionsGranted()) {
                                    downloader.downloadFile(data.file,mime!!,title)
                                }
                                else{
                                    showDialogPermissionRationale()
                                }

                            }

                            override fun onPermissionRationaleShouldBeShown(
                                p0: MutableList<PermissionRequest>?,
                                p1: PermissionToken?
                            ) {
                                showDialogPermissionRationale()
                            }
                        }).onSameThread().check()

                }
            }

        }

    }

    override fun getItemCount(): Int {
        return dataList.size

    }
    fun showDialogPermissionRationale(){
        AlertDialog.Builder(context)
            .setMessage("We need to access storage for download kindly, Provide with the Permissions")
            .setPositiveButton("Go to Settings"){
                    _,_ ->
                try {
                    val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package",pack,null)
                    intent.data=uri
                  context.startActivity(intent)
                }catch (e: ActivityNotFoundException){
                    e.printStackTrace()
                }

            }
            .setNegativeButton("Cancel"){
                dialog,_ ->
                dialog.dismiss()
            }.show()


    }
}

