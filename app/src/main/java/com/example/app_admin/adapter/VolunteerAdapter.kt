package com.example.app_admin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app_admin.R
import com.example.app_admin.model.VolunteerModel
import org.jetbrains.anko.find

class VolunteerAdapter(val volunteerModel: ArrayList<VolunteerModel>) :
    RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHodler>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): VolunteerAdapter.VolunteerViewHodler {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.move_volunteer_recycler_item, viewGroup, false)
        return VolunteerViewHodler(view)
    }

    override fun getItemCount(): Int = volunteerModel.size

    override fun onBindViewHolder(p0: VolunteerAdapter.VolunteerViewHodler, p1: Int) {
        p0.bind(volunteerModel[p1])
    }

    inner class VolunteerViewHodler(view: View) : RecyclerView.ViewHolder(view) {
        val textAuthor = view.find<TextView>(R.id.move_volunteer_recycler_item_title)
        val textNum = view.find<TextView>(R.id.move_volunteer_recycler_item_phoneNumber)
        val textDate = view.find<TextView>(R.id.move_volunteer_recycler_item_date)
        val textKakao = view.find<TextView>(R.id.move_volunteer_recycler_item_kakaoTalk_id)
        val textTime = view.find<TextView>(R.id.move_volunteer_recycler_item_time)
        fun bind(volunteerModel: VolunteerModel) {
            textAuthor.text = volunteerModel.author + " 의 이동봉사 신청"
            textNum.text = volunteerModel.phone
            textKakao.text = volunteerModel.phone
            textTime.text = volunteerModel.possible_time + " 부터"
            textDate.text = volunteerModel.creation_date
        }
    }
}