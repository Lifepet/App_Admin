package com.example.app_admin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_admin.R
import com.example.app_admin.adapter.AdoptAdapter
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.model.AdoptModel
import kotlinx.android.synthetic.main.adopt_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdoptFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.adopt_fragment, container, false) as ViewGroup
        getAdopt()
        return layout
    }

    fun getAdopt() {
        Connecter.api.getAdoptList("JWT eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoicXdlIiwidXNlcm5hbWUiOiJxd2UiLCJleHAiOjE1Njc5NTc5MDMsImVtYWlsIjoiIn0.x_nnkVHUqsRJwxPqC-djkQczXb4_4hqlBba6eMv3beQ")
            .enqueue(object : Callback<ArrayList<AdoptModel>> {

                override fun onResponse(call: Call<ArrayList<AdoptModel>>, response: Response<ArrayList<AdoptModel>>) {
                    val adoptModel = arrayListOf<AdoptModel>()
                    response.body()?.forEach {
                        adoptModel.add(
                            AdoptModel(
                                it.content,
                                it.kakao_id,
                                it.phone,
                                it.creation_date,
                                it.author,
                                it.current_location,
                                it.apply_id
                            )
                        )
                    }
                    adopt_fragment_recyclerView.adapter = AdoptAdapter(adoptModel)
                }

                override fun onFailure(call: Call<ArrayList<AdoptModel>>, t: Throwable) {
                }

            })
    }
}