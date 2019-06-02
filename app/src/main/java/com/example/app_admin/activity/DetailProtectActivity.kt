package com.example.app_admin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.app_admin.R
import com.example.app_admin.adapter.ProtectAdapter
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.model.ProtectModel
import com.example.app_admin.util.getToken
import kotlinx.android.synthetic.main.temporary_protect_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProtectActivity : AppCompatActivity() {
    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temporary_protect_detail)
        id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        temporary_protect_title.text = title + " 님의 임시보호 신청"
        temporary_protect_coment.text = "할말: " + intent.getStringExtra("content")
        temporary_protect_date.text = intent.getStringExtra("date")
        temporary_protect_kakaoTalk_id.text = intent.getStringExtra("kakao")
        temporary_protect_phoneNumber.text = intent.getStringExtra("phone")
        temporary_protect_term.text = intent.getStringExtra("month") + "달"
    }
}