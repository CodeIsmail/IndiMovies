package com.idealorb.indimovies.UI

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.idealorb.indimovies.R

class AiringFragment : Fragment() {

    companion object {
        fun newInstance() = AiringFragment()
    }

    private lateinit var viewModel: AiringViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.airing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AiringViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
