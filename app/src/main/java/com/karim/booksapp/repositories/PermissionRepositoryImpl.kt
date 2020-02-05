package com.karim.booksapp.repositories

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import javax.inject.Inject

class PermissionRepositoryImpl
@Inject
constructor(val app : Application): PermissionRepository {

    override fun isWriteExternalStoragePermissionGranted(): Boolean = (ContextCompat.checkSelfPermission(
            app,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)

    override fun isRequestPermissionGranted(grantResults: IntArray) : Boolean = (grantResults.isNotEmpty() && grantResults[0]
            == PackageManager.PERMISSION_GRANTED
            )

}