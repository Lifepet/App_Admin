//package com.example.app_user.connecter
//
//import com.example.app_admin.connecter.API
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object Connecter {
//    val retrofit: Retrofit
//    val api: API
//
//    init {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder().addInterceptor(interceptor)
//            .build()
//
//        retrofit = Retrofit
//            .Builder()
//            .baseUrl("http://54.180.159.21/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        api = retrofit.create(API::class.java)
//    }
//
//}