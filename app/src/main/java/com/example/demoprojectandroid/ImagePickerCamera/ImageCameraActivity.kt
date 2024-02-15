package com.example.demoprojectandroid.ImagePickerCamera

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.demoprojectandroid.APICall.APIClient.CommanAPI
import com.example.demoprojectandroid.APICall.APIClient.HttpParams
import com.example.demoprojectandroid.APICall.APIClient.OnDataResponseListner
import com.example.demoprojectandroid.databinding.ActivityImageCameraBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageCameraActivity : AppCompatActivity(), OnDataResponseListner {

    lateinit var binding: ActivityImageCameraBinding
    val CAMERA_PERMISSION_CODE = 1001
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    var ImagePath: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.opencamera.setOnClickListener(View.OnClickListener {
            if (hasCameraPermission()) {
                openCamera()
            } else {
                requestCameraPermission()
            }
        })

        binding.opengallery.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            selectimageIntent.launch(intent)
        })

        binding.callmultipartApi.setOnClickListener(View.OnClickListener {
         //   callmultipartapi()
        })

    }




//        private fun callmultipartapi() {
//
//
//            val commanAPI = CommanAPI("", this)
//            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
//            builder.setType(MultipartBody.FORM)
//            builder.addFormDataPart("message", "Test message")
//
//            val file: File? = ImagePath
//
//            if (file != null) {
//                builder.addFormDataPart("avatar", file.name, RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file))
//            }
//            commanAPI.doMultipartResponse(HttpParams.domultipartApi, builder.build(), "Test message")
//            Log.e("TAG", "onClick: " )
//
//
//            val requestBody = MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("message", message.path)
//                .addFormDataPart(
//                    "file",
//                    file.path,
//                    RequestBody.create(MediaType.parse("multipart/form-data"), File(file.path))
//                                )
//                .build()
//
//            val request = Request.Builder()
//                .url(yourApiUrl)
//                .post(requestBody)
//                .build()
//
//
//
//
//
//
//
//        }



    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        captureimageIntent.launch(cameraIntent)
    }

    var captureimageIntent: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val imageUri: Uri? = data?.data
                val imageBitmap = if (imageUri != null) {
                    // Decode the image file into a Bitmap
                    BitmapFactory.decodeFile(getRealPathFromUri(imageUri))
                } else {
                    // Fallback: Try to get a thumbnail as a Bitmap
                    data?.extras?.get("data") as? Bitmap
                }

                binding.imgImageView.setImageBitmap(imageBitmap);

                Log.e("TAG", "onActivityResult:ImagePath CAPTURE_IMAGE$data")
            }
        })

    // selectimage Intent
    var selectimageIntent: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> { result ->
            if (result.resultCode == RESULT_OK) {
                // selelct image get
                val data = result.data
                val filePath = data!!.data
                var bitmap: Bitmap? = null
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if(bitmap != null){
                    val capture: File? = CreateFile(bitmap)
                    ImagePath = capture;
                    binding.imgImageView.setImageBitmap(bitmap)
                    Log.e("TAG", "onActivityResult:ImagePath $ImagePath")
                }else{
                    Log.e("TAG", "onActivityResult:ImagePath Bitmap null")
                }


                Log.e("TAG", "onActivityResult:ImagePath SELECT_IMAGE$bitmap")
            }
        })



    //helper methods
    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {

        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE)
    }

    fun CreateFile(photo: Bitmap): File? {
        return if (photo != null) {
            val tsLong = System.currentTimeMillis() / 1000
            val f = File(cacheDir, "temp$tsLong.jpg")
            try {
                f.createNewFile()
                val bos = ByteArrayOutputStream()
                photo.compress(Bitmap.CompressFormat.JPEG, 80, bos)
                val bdata = bos.toByteArray()
                val fos = FileOutputStream(f)
                fos.write(bdata)
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
            return f
        } else null
    }
    private fun getRealPathFromUri(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        val filePath = cursor?.getString(columnIndex!!)
        cursor?.close()
        return filePath ?: ""
    }

    override fun Response(methodName: String?, response: String?, isResponse: Boolean) {

        Log.e(ContentValues.TAG, "Response: " + isResponse)
        Log.e(ContentValues.TAG, "Response: " + methodName)
        Log.e(ContentValues.TAG, "Response: " + response)
    }


}