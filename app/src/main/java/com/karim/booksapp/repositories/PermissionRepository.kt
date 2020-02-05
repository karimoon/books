package com.karim.booksapp.repositories

interface PermissionRepository {

    fun isWriteExternalStoragePermissionGranted(): Boolean

    fun isRequestPermissionGranted(grantResults: IntArray) : Boolean

}