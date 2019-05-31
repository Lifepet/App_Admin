package com.example.app_admin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app_admin.R
import com.example.app_admin.model.AdoptModel
import org.jetbrains.anko.find

class AdoptAdapter(val adoptModel: ArrayList<AdoptModel>) : RecyclerView.Adapter<AdoptAdapter.AdoptViewHodler>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): AdoptAdapter.AdoptViewHodler {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adopt_recycler_item, viewGroup, false)
        return AdoptViewHodler(view)
    }

    override fun getItemCount(): Int = adoptModel.size

    override fun onBindViewHolder(p0: AdoptAdapter.AdoptViewHodler, p1: Int) {
        p0.bind(adoptModel[p1])
    }

    inner class AdoptViewHodler(view: View) : RecyclerView.ViewHolder(view) {
        val textAuthor = view.find<TextView>(R.id.adopt_recycler_item_title)
        val textNum = view.find<TextView>(R.id.adopt_recycler_item_phoneNumber)
        val textKakao = view.find<TextView>(R.id.adopt_recycler_item_kakaoTalk_id)
        val textLocation = view.find<TextView>(R.id.adopt_recycler_item_location)
        val textDate = view.find<TextView>(R.id.adopt_recycler_item_date)
        fun bind(adoptModel: AdoptModel) {
            textAuthor.text = adoptModel.author + " 의 입양신청"
            textDate.text = adoptModel.creation_date
            textKakao.text = adoptModel.kakao_id
            textNum.text = adoptModel.phone
            textLocation.text = adoptModel.current_location
        }
    }
}