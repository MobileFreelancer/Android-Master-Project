package com.example.demoprojectandroid.APICall.APIClient

import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException


class CommanAPI(private val methodName: String, private val onDataResponseListner: OnDataResponseListner) {

    companion object {
        // Static variable
        var TAG: String = "CommanAPI"
    }

    private var responceData: String? = null

    fun getResponse(Url: String) {
        try {
            val apiService = RetrofitClient().createService(RetrofitService::class.java)
            val call = apiService.getData(Url)
            Log.e("TAG", "URL: " + call.request().url + "....")
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                    if (response.body() != null) {
                        try {
                            responceData = response.body()!!.string()
                            Log.e("TAG", "onResponse: $responceData")
                            onDataResponseListner.Response(methodName, responceData, response.isSuccessful)
                        } catch (e: IOException) {
                            Log.e("TAG", "Exception = " + e.message)
                            onDataResponseListner.Response(methodName, responceData, response.isSuccessful)
                        }
                    } else {
                        onDataResponseListner.Response(methodName, "NotReachable", false)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e(TAG, "onFailure: " + t.message)
                    onDataResponseListner.Response(methodName, "", false)
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, "getResponse: " + e.message)
        }
    }



//    fun doMultipartResponse(url: String, requestBody: File?, s: String) {
//
//
//
//
//        val file = File("path/to/your/file.jpg") // Replace with the actual file path
//        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
//        val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)
//
//        val apiService = RetrofitClient.apiService
//        val responseBodyCall = apiService.uploadFile("your_upload_endpoint", filePart)
//
//        responseBodyCall.enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.isSuccessful) {
//                    // Handle the success response
//                    val responseBody = response.body()
//                    // Do something with the response body
//                } else {
//                    // Handle the error response
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                // Handle failure
//            }
//        })
//
//
//
//
//        val apiService = RetrofitClient().createService(RetrofitService::class.java)
//
//        val responseBodyCall: Call<ResponseBody> = apiService.uploadMultiFile(url, requestBody)
//        responseBodyCall.enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.body() != null) {
//                    try {
//                        responceData = response.body()!!.string()
//                        Log.e(TAG, "onResponse: $responceData")
//                        onDataResponseListner.Response(methodName, responceData, response.isSuccessful)
//                    } catch (e: IOException) {
//                        onDataResponseListner.Response(methodName, "", false)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.e(TAG, "onResponse: $t")
//                onDataResponseListner.Response(methodName, "", false)
//            }
//        })
//    }


}