package com.example.demoprojectandroid.APICall

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.demoprojectandroid.APICall.APIClient.CommanAPI
import com.example.demoprojectandroid.APICall.APIClient.HttpParams
import com.example.demoprojectandroid.APICall.APIClient.OnDataResponseListner
import com.example.demoprojectandroid.databinding.ActivityApidemoBinding
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class APIDemoActivity : AppCompatActivity(), OnDataResponseListner {

    lateinit var binding: ActivityApidemoBinding
    var ImagePath: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApidemoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.GetApicall.setOnClickListener(View.OnClickListener {
            callapi()
        })
        callmultipartapi()
    }


    private fun callapi() {
        val commanAPI = CommanAPI("DELETEACCOUNT", this) //methodname interface
        commanAPI.getResponse("https://jsonplaceholder.typicode.com/posts")
        Log.e("TAG", "DELETEPROMISE:URL:::: " + HttpParams.getPosts)
    }

    private fun callmultipartapi() {


//        val commanAPI = CommanAPI("", this)
//        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
//        builder.setType(MultipartBody.FORM)
//        builder.addFormDataPart("message", "Test message")
//
//        val file: File? = ImagePath
//
//        if (file != null) {
//            builder.addFormDataPart("avatar", file.name, RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file))
//        }
//        commanAPI.doMultipartResponse(HttpParams.APPURL + "update_profile", file,"Test message")
//        Log.e("TAG", "onClick: " )
    }


    override fun Response(methodName: String?, response: String?, isResponse: Boolean) {

        Log.e(TAG, "Response: " + isResponse)
        Log.e(TAG, "Response: " + methodName)
        Log.e(TAG, "Response: " + response)
        binding.jsonresponse.text = response + " "
    }

}