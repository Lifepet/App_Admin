package com.example.app_admin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_admin.R
import com.example.app_admin.adapter.ProtectAdapter
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.model.ProtectModel
import com.example.app_admin.util.getToken
import kotlinx.android.synthetic.main.temporary_protect_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProtectFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.temporary_protect_fragment, container, false) as ViewGroup
        protect()
        return layout
    }

    fun protect() {
        Connecter.api.getProtectList(getToken(activity!!.applicationContext))
            .enqueue(object : Callback<ArrayList<ProtectModel>> {
                override fun onFailure(call: Call<ArrayList<ProtectModel>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<ArrayList<ProtectModel>>,
                    response: Response<ArrayList<ProtectModel>>
                ) {
                    val protectModel = arrayListOf<ProtectModel>()
                    response.body()?.forEach {
                        protectModel.add(
                            ProtectModel(
                                it.apply_id,
                                it.author,
                                it.creation_date,
                                it.possible_month,
                                it.current_location,
                                it.phone
                            )
                        )
                    }
                    temporary_protect_fragment_recyclerView.adapter = ProtectAdapter(protectModel)
                }

            })
    }
}