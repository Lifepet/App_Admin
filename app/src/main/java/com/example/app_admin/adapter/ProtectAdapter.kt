package com.example.app_admin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app_admin.R
import com.example.app_admin.model.ProtectModel
import org.jetbrains.anko.find

class ProtectAdapter(val protectModel: ArrayList<ProtectModel>) :
    RecyclerView.Adapter<ProtectAdapter.ProtectViewHodler>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ProtectAdapter.ProtectViewHodler {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.temporary_protect_recycler_item, viewGroup, false)
        return ProtectViewHodler(view)
    }

    override fun getItemCount(): Int = protectModel.size

    override fun onBindViewHolder(p0: ProtectAdapter.ProtectViewHodler, p1: Int) {
        p0.bind(protectModel[p1])
    }

    inner class ProtectViewHodler(view: View) : RecyclerView.ViewHolder(view) {
        val textAuthor = view.find<TextView>(R.id.temporary_protect_recycler_item_title)
        val textNum = view.find<TextView>(R.id.temporary_protect_recycler_item_phoneNumber)
        val textKakao = view.find<TextView>(R.id.temporary_protect_recycler_item_kakaoTalk_id)
        val textDate = view.find<TextView>(R.id.temporary_protect_recycler_item_date)
        val textTime = view.find<TextView>(R.id.temporary_protect_recycler_item_term)
        fun bind(protectModel: ProtectModel) {
            textAuthor.text = protectModel.author + " 의 임시보호 신청"
            textNum.text = protectModel.phone
            textKakao.text = protectModel.phone
            textDate.text = protectModel.creation_date
            textTime.text = protectModel.possible_month+"달"
        }
    }
}