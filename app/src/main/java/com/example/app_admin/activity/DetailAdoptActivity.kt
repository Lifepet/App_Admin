package com.example.app_admin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.example.app_admin.R
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.model.AdoptModel
import com.example.app_admin.util.getToken
import kotlinx.android.synthetic.main.adopt_detail.*
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAdoptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adopt_detail)
        val intent = intent
        val id = intent.getStringExtra("id")
        val textTitle = find<TextView>(R.id.adopt_deatail_title)
        adopt_deatail_coment.text = "할말: " + intent.getStringExtra("content")
        adopt_detail_phoneNumber.text = intent.getStringExtra("phone")
        adopt_deatail_location.text = intent.getStringExtra("location")
        adopt_detail_kakaoTalk_id.text = intent.getStringExtra("kakao")
        adopt_deatail_title.text = intent.getStringExtra("title") + " 님의 입양신청"
        adopt_deatail_date.text = intent.getStringExtra("date")
    }
}