package com.idealorb.tracketv.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.idealorb.tracketv.R
import com.idealorb.tracketv.adapter.TvShowCategoryAdapter
import com.idealorb.tracketv.extensions.display
import com.idealorb.tracketv.extensions.hide
import com.idealorb.tracketv.model.TvShow
import com.idealorb.tracketv.model.TvShowCategoryEntity
import kotlinx.android.synthetic.main.fragment_tvshow_discover.*

class TvShowDiscoverFragment : Fragment() {

    private val viewModel: TvShowDiscoverViewModel by viewModels()
    private lateinit var categoryAdapter: TvShowCategoryAdapter
    private val tvShowData: MutableList<TvShow?> = ArrayList()

    companion object {
        fun newInstance() = TvShowDiscoverFragment()
        private val TAG = TvShowDiscoverFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_tvshow_discover, container, false)
    }

    private fun initView(tvShowCategoryEntities: List<TvShowCategoryEntity>) {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context,
                    VERTICAL, false)
            adapter = TvShowCategoryAdapter(tvShowCategoryEntities)
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
