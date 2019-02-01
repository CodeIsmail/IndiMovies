package com.idealorb.tracketv.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.idealorb.tracketv.R

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
