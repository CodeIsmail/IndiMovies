package com.idealorb.tracketv.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.idealorb.tracketv.R
import com.idealorb.tracketv.adapter.TvShowInfoPagerAdapter
import kotlinx.android.synthetic.main.tv_show_fragment.*

class TvShowFragment : Fragment() {

    companion object {
        fun newInstance() = TvShowFragment()
        val INTENT_MESSAGE = "TV_SHOW"
    }

    private lateinit var viewModel: TvShowViewModel
    val args: TvShowFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.tv_show_fragment, container, false)

        val pagerAdapter = TvShowInfoPagerAdapter(context!!, childFragmentManager)
        view_pager.apply{
            adapter = pagerAdapter
        }
        tabLayout.setupWithViewPager(view_pager)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val tvShowDiscoverViewModelFactory = TvShowDiscoverViewModelFactory.createFactory()
        viewModel = ViewModelProviders.of(this, tvShowDiscoverViewModelFactory).get(TvShowViewModel::class.java)

        viewModel.getTvShowDetails(args.tvShowId).observe(this, Observer {
            tv_show_title_tv.text = it.originalName
        })
    }


}
