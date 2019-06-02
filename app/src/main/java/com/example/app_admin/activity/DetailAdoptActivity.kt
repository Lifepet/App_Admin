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
        Connecter.api.getAdoptDetail(getToken(applicationContext), id).enqueue(object : Callback<AdoptModel> {
            override fun onFailure(call: Call<AdoptModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<AdoptModel>, response: Response<AdoptModel>) {
                val adoptModel = response.body()!!
                Log.d("test", adoptModel.author)
                textTitle.text = adoptModel.author + "님의 입양신청"
                adopt_deatail_coment.text = "할말: " + adoptModel.content
                adopt_deatail_date.text = adoptModel.creation_date
                adopt_deatail_location.text = adoptModel.current_location
                adopt_detail_phoneNumber.text = adoptModel.phone
            }

        })
    }
}