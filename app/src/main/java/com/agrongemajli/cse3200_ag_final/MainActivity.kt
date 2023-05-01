package com.agrongemajli.cse3200_ag_final

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.agrongemajli.cse3200_ag_final.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //NOTE: did not need to set user-permission for camera. That caused an error because of deprication.
    //The program will automatically call for camera access using the Intent below.

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //instantiate the binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        //set the onClick for the Take Photo Button
        binding.llTakePhotoButton.setOnClickListener {
            //Set the intent to capture an image.
            // This calls the camera app to take the photo
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //With the results, pass that into the onActivityResukt function
            startActivityForResult(cameraIntent, 200)
        }
        //Set the content view to activity_main
        setContentView(binding.root)
    }

    //This function comes from Google Camera Basics
    //https://developer.android.com/training/camera-deprecated/photobasics
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Check the result code is what we want for success and that we got an image back
        if (resultCode == Activity.RESULT_OK && requestCode == 200 && data != null){

            //Here we want some logic to move the "current" photo to previous and new into current.
            //First, set previous photo = ImageBitmap data of Current Photo.
            //Logic to check if null first.
            if(binding.llImageView1.drawable != null)
                binding.llImageView2.setImageBitmap(binding.llImageView1.drawable.toBitmap())

            //Second, set the 'current photo' = data we just got from the camera.
            binding.llImageView1.setImageBitmap(data.extras?.get("data") as Bitmap)

        }
    }

}