package com.karim.booksapp.screens.main.fragments


import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.karim.booksapp.R
import com.karim.booksapp.repositories.PermissionRepository
import dagger.android.support.DaggerFragment
import javax.inject.Inject

const val PERMISSION_REQUEST_CODE = 1001

class SplashFragment : DaggerFragment() {

    @Inject
    lateinit var permissionRepository: PermissionRepository

    override fun onResume() {
        super.onResume()
        ( activity as AppCompatActivity).supportActionBar?.show()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (permissionRepository.isWriteExternalStoragePermissionGranted()) {
            displayMainFragment()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissionRepository.isRequestPermissionGranted(grantResults)) {
                displayMainFragment()
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    private fun displayMainFragment() {
        val navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )
        navController.navigate(
            R.id.action_nav_main, null,
            NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, true)
                .build()
        )
    }

}
