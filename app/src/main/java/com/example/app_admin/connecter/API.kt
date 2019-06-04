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

    @GET("/apply/adoption/{post_id}")
    @Headers("Content-Type: application/json")
    fun getAdoptDetail(@Header("Authorization") token: String, @Path("post_id") postId: String?): Call<AdoptModel>

    @GET("/apply/care/{post_id}")
    @Headers("Content-Type: application/json")
    fun getProtectLDetail(@Header("Authorization") token: String, @Path("post_id") postId: String?): Call<ProtectModel>

    @GET("/apply/move/{post_id}")
    @Headers("Content-Type: application/json")
    fun getVolunteerDetail(@Header("Authorization") token: String, @Path("post_id") postId: String?): Call<VolunteerModel>

    @POST("post/adoption/")
    @Multipart
    fun postAdopt(
        @Header("Authorization") token: String, @Part("title") title: RequestBody, @Part("sex") sex: RequestBody,
        @Part("content") content: RequestBody, @Part("age") age: RequestBody, @Part("kind") kind: RequestBody, @Part image: MultipartBody.Part
    ): Call<Unit>

    @POST("post/care/")
    @Multipart
    fun postCare(
        @Header("Authorization") token: String, @Part("title") title: RequestBody, @Part("sex") sex: RequestBody,
        @Part("content") content: RequestBody, @Part("age") age: RequestBody, @Part("kind") kind: RequestBody, @Part("current_location") location: RequestBody, @Part image: MultipartBody.Part
    ): Call<Unit>

    @POST("post/move/")
    @Multipart
    fun postVolunteer(
        @Header("Authorization") token: String, @Part("title") title: RequestBody, @Part("sex") sex: RequestBody,
        @Part("content") content: RequestBody, @Part("kind") kind: RequestBody, @Part("current_location") location: RequestBody, @Part(
            "destination_location"
        ) end_location: RequestBody, @Part("age") age: RequestBody, @Part image: MultipartBody.Part
    ): Call<Unit>
}