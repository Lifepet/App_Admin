package com.example.app_admin

import android.content.Context
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.app_admin.activity.AdoptFragment
import com.example.app_admin.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_main.adapter = PagerAdapter(supportFragmentManager)
        view_main.currentItem = 0
        tab_main.addTab(tab_main.newTab().setText("입양"))
        tab_main.addTab(tab_main.newTab().setText("임시보호"))
        tab_main.addTab(tab_main.newTab().setText("이동봉사"))
        supportFragmentManager.beginTransaction().replace(R.id.view_main, AdoptFragment()).commit()

        view_main.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_main))

        tab_main.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                view_main.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }


            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}
