package com.idealorb.indimovies.UI

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.idealorb.indimovies.R

class ShowTrackingFragment : Fragment() {

    companion object {
        fun newInstance() = ShowTrackingFragment()
    }

    private lateinit var viewModel: ShowTrackingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.show_tracking_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShowTrackingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
