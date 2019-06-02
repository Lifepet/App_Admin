package com.example.app_admin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_admin.R
import com.example.app_admin.adapter.VolunteerAdapter
import com.example.app_admin.connecter.Connecter
import com.example.app_admin.model.VolunteerModel
import com.example.app_admin.util.RecyclerItemClickListener
import com.example.app_admin.util.getToken
import kotlinx.android.synthetic.main.move_volunteer_fragment.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VolunteerFragment : Fragment() {
    val volunteerModel = arrayListOf<VolunteerModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.move_volunteer_fragment, container, false) as ViewGroup
        val volunteerRecycler = layout.find<RecyclerView>(R.id.move_volunteer_fragment_recyclerView)
        volunteer()
        volunteerRecycler.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                volunteerRecycler,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onLongItemClick(view: View?, position: Int) {

                    }

                    override fun onItemClick(view: View, position: Int) {
                        activity!!.startActivity<DetailVolunteerActivity>(
                            "content" to volunteerModel[position].content,
                            "date" to volunteerModel[position].creation_date,
                            "phone" to volunteerModel[position].phone,
                            "title" to volunteerModel[position].author,
                            "time" to volunteerModel[position].possible_time
                        )
                    }
                })
        )
        return layout
    }

    fun volunteer() {
        Connecter.api.getVolunteerList(getToken(activity!!.applicationContext))
            .enqueue(object : Callback<ArrayList<VolunteerModel>> {
                override fun onFailure(call: Call<ArrayList<VolunteerModel>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<ArrayList<VolunteerModel>>,
                    response: Response<ArrayList<VolunteerModel>>
                ) {
                    response.body()?.forEach {
                        volunteerModel.add(
                            VolunteerModel(
                                it.apply_id,
                                it.author,
                                it.creation_date,
                                it.phone,
                                it.possible_time,
                                it.content
                            )
                        )
                    }
                    move_volunteer_fragment_recyclerView.adapter = VolunteerAdapter(volunteerModel)
                }

            })
    }
}
