package com.example.app_admin.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_admin.R
import com.example.app_admin.adapter.ProtectAdapter
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.model.ProtectModel
import com.example.app_admin.util.RecyclerItemClickListener
import com.example.app_admin.util.getToken
import kotlinx.android.synthetic.main.temporary_protect_fragment.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProtectFragment : Fragment() {
    val protectModel = arrayListOf<ProtectModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.temporary_protect_fragment, container, false) as ViewGroup
        val protectRecyclerView = layout.find<RecyclerView>(R.id.temporary_protect_fragment_recyclerView)
        val protectFab = layout.find<FloatingActionButton>(R.id.temporary_protect_fragment_fab)
        protect()
        protectRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                protectRecyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onLongItemClick(view: View?, position: Int) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onItemClick(view: View, position: Int) {
                        activity!!.startActivity<DetailProtectActivity>(
                            "id" to protectModel[position].apply_id,
                            "title" to protectModel[position].author,
                            "kakao" to protectModel[position].phone,
                            "phone" to protectModel[position].phone,
                            "content" to protectModel[position].content,
                            "month" to protectModel[position].possible_month,
                            "date" to protectModel[position].creation_date
                        )
                    }

                })
        )
        protectFab.setOnClickListener { activity!!.startActivity<ProtectWriteActivity>() }
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
                    response.body()?.forEach {
                        protectModel.add(
                            ProtectModel(
                                it.apply_id,
                                it.author,
                                it.creation_date,
                                it.possible_month,
                                it.current_location,
                                it.phone,
                                it.content
                            )
                        )
                    }
                    temporary_protect_fragment_recyclerView.adapter = ProtectAdapter(protectModel)
                }

            })
    }
}