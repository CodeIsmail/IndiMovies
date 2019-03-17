package com.idealorb.tracketv.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.idealorb.tracketv.R
import com.idealorb.tracketv.adapter.MainViewAdapter
import com.idealorb.tracketv.extensions.display
import com.idealorb.tracketv.extensions.hide
import com.idealorb.tracketv.model.MainModel
import com.idealorb.tracketv.model.TvShow
import kotlinx.android.synthetic.main.tv_show_discover_fragment.*

class TvShowDiscoverFragment : Fragment() {

    private lateinit var viewModel: TvShowDiscoverViewModel
    private lateinit var mainAdapter: MainViewAdapter
    private val tvShowData: MutableList<TvShow?> = ArrayList()

    companion object {
        fun newInstance() = TvShowDiscoverFragment()
        private val TAG = TvShowDiscoverFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.tv_show_discover_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: called")

        val tvShowDiscoverViewModelFactory = TvShowDiscoverViewModelFactory.createFactory()
        viewModel = ViewModelProviders.of(this, tvShowDiscoverViewModelFactory).get(TvShowDiscoverViewModel::class.java)

        viewModel.mainView.observe(this, Observer<List<MainModel>> {
            if (it.isNullOrEmpty()) {
                showErrorMessage()
                Log.d(TAG, "viewmodel: called ${it.size}")
            } else {
                showTvShowDataView()
                if (recycler_view.adapter == null) {
                    initView(it)
                } else {
                    (recycler_view.adapter as MainViewAdapter).updateMainModelData(it)
                }
            }
        })


    }

    private fun initView(mainModels: List<MainModel>) {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context,
                    VERTICAL, false)
            adapter = MainViewAdapter(mainModels)
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
