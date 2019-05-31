package com.example.app_admin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.model.LoginModel
import com.example.app_admin.util.saveToken
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bt_login.setOnClickListener { login() }

    }

    fun login() {
        Connecter.api.postLogin(hashMapOf("username" to edit_id.text.toString(), "password" to edit_pw.text.toString()))
            .enqueue(object : Callback<LoginModel> {
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    when (response.code()) {
                        200 -> {
                            toast("성공!")
                            saveToken(applicationContext, response.body()!!.token)
                            startActivity<MainActivity>()
                        }
                        403 -> toast("아이디 혹은 비밀번호가 틀립니다")
                    }
                }

            })
    }
}