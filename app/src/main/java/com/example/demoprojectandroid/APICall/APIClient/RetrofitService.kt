package com.example.demoprojectandroid.APICall.APIClient

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Url

interface RetrofitService {

    @POST
    @FormUrlEncoded
    fun doGetUserList(@Url url: String, @FieldMap params: Map<String, String>): Call<ResponseBody>

    @Multipart
    @POST
    fun uploadMultiFile(@Url url: String, @Part params: RequestBody?): Call<ResponseBody>



    @Multipart
    @POST("/images/upload")
    fun uploadImage(@Part image: MultipartBody.Part): Call<ResponseBody>


    @Headers("Content-Type: application/json")
    @POST
    fun getResponseData(@Url url: String, @Body body: RequestBody): Call<ResponseBody>

    @GET
    fun getData(@Url url: String): Call<ResponseBody>

    @POST
    fun getPost(@Url url: String): Call<ResponseBody>

}