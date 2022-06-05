package com.dicoding.budayai.detail.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.budayai.R
import com.dicoding.budayai.detail.DeskripsiFragment
import com.dicoding.budayai.detail.SejarahFragment

@Suppress("DEPRECATION")
class PageAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = DeskripsiFragment()
            1 -> fragment = SejarahFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.title_tab_description,
            R.string.title_tab_sejarah
        )
    }
}