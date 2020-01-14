package com.idealorb.tracketv.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.idealorb.tracketv.R

class TvShowTimelineFragment : Fragment() {

    companion object {
        fun newInstance() = TvShowTimelineFragment()
    }

    private lateinit var timelineViewModel: TvShowTimelineViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tvshow_timeline, container, false)
    }

}
