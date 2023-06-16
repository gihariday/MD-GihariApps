package com.dicoding.c23_pc713.gihariapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dicoding.c23_pc713.gihariapp.databinding.ActivityGalleryBinding

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    private val PERMISSION_REQUEST_CODE = 200
    private val GALLERY_REQUEST_CODE = 100
    private val CAMERA_REQUEST_CODE = 101

    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        val view = binding.root

        // Set onClickListener for back button
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        setContentView(view)

        binding.btnSelectFromGallery.setOnClickListener {
            if (checkPermissions()) {
                openGallery()
            } else {
                requestPermissions()
            }
        }

        binding.btnTakePhoto.setOnClickListener {
            if (checkPermissions()) {
                openCamera()
            } else {
                requestPermissions()
            }
        }

        binding.btnSave.setOnClickListener {
            // Logika untuk menyimpan foto
        }
    }

    private fun checkPermissions(): Boolean {
        val resultGallery = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val resultCamera = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )
        return resultGallery == PackageManager.PERMISSION_GRANTED && resultCamera == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ),
            PERMISSION_REQUEST_CODE
        )
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    val selectedImage = data?.data
                    binding.imagePreview.setImageURI(selectedImage)
                    binding.imagePreview.visibility = View.VISIBLE
                    binding.btnSave.visibility = View.VISIBLE
                }
                CAMERA_REQUEST_CODE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    binding.imagePreview.setImageBitmap(imageBitmap)
                    binding.imagePreview.visibility = View.VISIBLE
                    binding.btnSave.visibility = View.VISIBLE
                }
            }
        }
    }
}