package com.idealorb.tracketv.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.idealorb.tracketv.R
import com.idealorb.tracketv.adapter.TvShowAdapter
import com.idealorb.tracketv.extensions.display
import com.idealorb.tracketv.extensions.hide
import dev.codeismail.domain.common.Result
import dev.codeismail.domain.model.TvShow
import kotlinx.android.synthetic.main.fragment_tvshow_discover.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowDiscoverFragment : Fragment() {

    private val viewModel: TvShowDiscoverViewModel by viewModel()
    private val tvShowAdapter: TvShowAdapter = TvShowAdapter()
    private val tvShowData: MutableList<TvShow?> = ArrayList()

    companion object {
        fun newInstance() = TvShowDiscoverFragment()
        private val TAG = TvShowDiscoverFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_tvshow_discover, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tvShowAdapter
        }
        viewModel.getData().observe(viewLifecycleOwner){
            when(it){
                is Result.Success -> {
                    tvShowAdapter.submitList(it.data)
                    showTvShowDataView()
                }
                is Result.Loading -> {
                    progressBar.display()
                }
                is Result.Error -> {
                    Log.d(TAG, "Hello Error")
                    showErrorMessage()
                }
            }
        }
    }

    private fun showTvShowDataView() {
        tv_error_message.hide()
        progressBar.hide()
        recycler_view.display()
    }

    private fun showErrorMessage() {
        progressBar.hide()
        recycler_view.hide()
        tv_error_message.display()
    }

}
