package com.example.app_admin.adapter

import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.app_admin.activity.AdoptFragment
import com.example.app_admin.activity.ProtectFragment
import com.example.app_admin.activity.VolunteerFragment

class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return AdoptFragment()
            1 -> return ProtectFragment()
            2 -> return VolunteerFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }

}