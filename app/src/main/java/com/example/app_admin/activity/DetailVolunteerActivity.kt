package com.example.app_admin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.app_admin.R
import kotlinx.android.synthetic.main.move_volunteer_detail.*

class DetailVolunteerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.move_volunteer_detail)
        Log.d("content", intent.getStringExtra("content"))
        move_volunteer_deatail_date.text = intent.getStringExtra("date")
        move_volunteer_deatail_phoneNumber.text = intent.getStringExtra("phone")
        move_volunteer_deatail_time.text = intent.getStringExtra("time") + "시 부터"
        move_volunteer_deatail_title.text = intent.getStringExtra("title") + " 님의 이동봉사 신청"
        move_volunteer_deatail_kakaoTalk_id.text = intent.getStringExtra("phone")
        textView10.text = intent.getStringExtra("content")

    }
}