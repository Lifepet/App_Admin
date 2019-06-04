package com.example.app_admin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.app_admin.connecter.Connecter
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        button.setOnClickListener {
            if (edit_pw.equals(edit_pwcheck)) {
                if (edit_code.equals("6902d3a1-b15d-4aa1-8bbc-c957294493d1")) {
                    postSignup()
                } else {
                    toast("관리자 코드가 바르지 않습니다.")
                }
            } else {
                toast("비밀번호 입력이 바르지 않습니다.")
            }
        }

    }

    fun postSignup() {
        Connecter.api.postAuth(
            hashMapOf(
                "username" to edit_id.text.toString(),
                "name" to edit_name.text.toString(),
                "pet" to 0,
                "password" to edit_pw.text.toString(),
                "isAdmin" to true
            )
        ).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.code() == 201) {
                    toast("회원가입이 완료되었습니다.")
                    startActivity<LoginActivity>()
                }
            }

        })

    }
}