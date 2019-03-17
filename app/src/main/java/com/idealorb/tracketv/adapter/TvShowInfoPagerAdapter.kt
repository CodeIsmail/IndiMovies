package com.idealorb.tracketv.adapter

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.idealorb.tracketv.R
import com.idealorb.tracketv.UI.CastFragment
import com.idealorb.tracketv.UI.SeasonFragment
import com.idealorb.tracketv.UI.SimilarShowFragment
import com.idealorb.tracketv.UI.SynopsisContentFragment

class TvShowInfoPagerAdapter(val context: Context, fm : FragmentManager) : FragmentPagerAdapter(fm){

    private val tabTitles = arrayListOf(R.string.synopsis_title,
            R.string.seasons_title, R.string.cast_title, R.string.similar_shows_title)
    override fun getItem(position: Int): Fragment {
        Log.d("TvShowInfoPagerAdapter", "getItem: $position called")
        return when(position){
            0 -> SynopsisContentFragment.newInstance()
            1 -> SeasonFragment.newInstance()
            2 -> CastFragment.newInstance()
            else -> SimilarShowFragment.newInstance()
        }
    }

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(tabTitles[position])
    }

}