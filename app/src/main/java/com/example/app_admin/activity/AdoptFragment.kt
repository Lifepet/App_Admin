package com.example.app_admin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_admin.R
import com.example.app_admin.adapter.AdoptAdapter
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.model.AdoptModel
import com.example.app_admin.util.RecyclerItemClickListener
import com.example.app_admin.util.getToken
import kotlinx.android.synthetic.main.adopt_fragment.*
import kotlinx.android.synthetic.main.temporary_protect_fragment.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdoptFragment : Fragment() {
    val adoptModel = arrayListOf<AdoptModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.adopt_fragment, container, false) as ViewGroup
        val adoptRecyclerView = layout.find<RecyclerView>(R.id.adopt_fragment_recyclerView)
        getAdopt()
        adoptRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                adoptRecyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onLongItemClick(view: View?, position: Int) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onItemClick(view: View, position: Int) {
                        activity?.startActivity<DetailAdoptActivity>("id" to adoptModel[position].apply_id)
                    }

                })
        )
        return layout
    }

    fun getAdopt() {
        Connecter.api.getAdoptList(getToken(activity!!.applicationContext))
            .enqueue(object : Callback<ArrayList<AdoptModel>> {

                override fun onResponse(call: Call<ArrayList<AdoptModel>>, response: Response<ArrayList<AdoptModel>>) {
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