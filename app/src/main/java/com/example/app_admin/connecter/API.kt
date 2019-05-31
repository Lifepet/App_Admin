package com.example.app_admin.connecter

import com.example.app_admin.model.AdoptModel
import com.example.app_admin.model.LoginModel
import com.example.app_admin.model.ProtectModel
import com.example.app_admin.model.VolunteerModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST


interface API {
    @POST("/auth/joinAdmin/")
    fun postAuth(@Body body: Any): Call<Unit>

    @POST("/auth/loginAdmin/")
    fun postLogin(@Body body: Any): Call<LoginModel>

    @GET("/apply/adoption/")
    @Headers("Content-Type: application/json")
    fun getAdoptList(@Header("Authorization") token: String): Call<ArrayList<AdoptModel>>

    @GET("/apply/care/")
    @Headers("Content-Type: application/json")
    fun getProtectList(@Header("Authorization") token: String): Call<ArrayList<ProtectModel>>

    @GET("/apply/move/")
    @Headers("Content-Type: application/json")
    fun getVolunteerList(@Header("Authorization") token: String): Call<ArrayList<VolunteerModel>>
}