package com.example.a91squareftassignment.download

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    private val context: Context
    ) : Downloader
{

    private var downloadManager = context.getSystemService(DownloadManager::class.java)


    override fun downloadFile(url: String, mimeType:String,title:String): Long {
        val request =  DownloadManager.Request(url.toUri())
            .setMimeType(mimeType)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .addRequestHeader("Authorisation","Bearer <token>")
            .setTitle(title)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,mimeType)
        return downloadManager.enqueue(request)
    }
}