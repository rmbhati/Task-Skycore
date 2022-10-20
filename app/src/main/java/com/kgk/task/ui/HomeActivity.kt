package com.kgk.task.ui

import android.view.View
import android.widget.SeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kgk.task.R
import com.kgk.task.base.BaseActivity
import com.kgk.task.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {
    private val viewModel by viewModel<HomeViewModel>()
    private var pageCount = 1
    private var radius = 1000
    private var totalPage = -1

    private var adapter: HomeAdapter? = null
    private var listData: List<BusinessesData> = ArrayList()
    private var loading = true
    private var pastVisiblesItems = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    override fun initView() {
        setContentView(R.layout.activity_main)
        viewModel.fetchingData.observe(this) { showLoading(it) }
        viewModel.errorData.observe(this) { onFailed(it) }
        viewModel.homeData.observe(this) { onSuccess(it) }
    }

    override fun initData() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                radius = progress
                if (progress < 1000) {
                    tvRadius.text = "$progress M"
                } else {
                    val km = progress / 1000
                    tvRadius.text = "$km KM"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        refresh.setOnRefreshListener {
            pageCount = 1
            listData = ArrayList()
            recyclerView.adapter = null
            viewModel.getHomeData(radius, pageCount)
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        viewModel.getHomeData(radius, pageCount)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            pageCount += 1
                            if (totalPage < pageCount * 15) {
                                loading = false
                                toast("No more data available")
                            } else {
                                viewModel.getHomeData(radius, pageCount)
                            }
                        }
                    }
                }
            }
        })
    }

    private fun showLoading(status: Boolean) {
        if (status) {
            progressbar.visibility = View.VISIBLE
        } else {
            progressbar.visibility = View.INVISIBLE
            refresh.isRefreshing = false
        }
    }

    private fun onSuccess(response: HomeResponse) {
        totalPage = response.total
        if (response.businessesData.isNotEmpty())
            loading = true
        //listData.addAll( response.businessesData)
        listData = response.businessesData
        adapter = HomeAdapter(this, listData)
        recyclerView.adapter = adapter
    }

    private fun onFailed(error: String) {
        toast(error)
    }
}