package com.huya.pitaya.pagestatustransformer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_issue1.*

/**
 * @author YvesCheung
 * 2021/9/6
 */
class Issue1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue1)
        val fragments = arrayListOf<Fragment>(
            RecyclerViewFragment(false),
            RecyclerViewFragment(false),
            RecyclerViewFragment(false),
            RecyclerViewFragment(false)
        )
        val loadingPages = arrayListOf<Fragment>(
            RecyclerViewFragment(true),
            RecyclerViewFragment(true),
            RecyclerViewFragment(true),
            RecyclerViewFragment(true)
        )
        val crashs = arrayListOf<Fragment>(
            RecyclerViewFragmentCrash(),
            RecyclerViewFragmentCrash(),
            RecyclerViewFragmentCrash(),
            RecyclerViewFragmentCrash()
        )
        // 产生crash 情况
//        view_pager.adapter = ViewPagerAdapter(crashs, supportFragmentManager)
        // 使用状态布局后无法显示内同
        view_pager.adapter = ViewPagerAdapter(loadingPages, supportFragmentManager)
        // 原生
//        view_pager.adapter = ViewPagerAdapter(fragments, supportFragmentManager)

    }

    class ViewPagerAdapter(val fragments: ArrayList<Fragment>, manager: FragmentManager) :
        FragmentPagerAdapter(manager) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]

        }

        override fun getCount(): Int {
            return fragments.size
        }
    }

}