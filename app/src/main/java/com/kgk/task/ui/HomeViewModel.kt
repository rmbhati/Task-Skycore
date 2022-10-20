package com.kgk.task.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData

import com.kgk.task.base.BaseViewModel
import com.kgk.task.network.APIEndPoints
import com.kgk.task.network.APIMethods
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val api: APIMethods) : BaseViewModel() {
    var fetchingData: MutableLiveData<Boolean> = MutableLiveData()
    var homeData: MutableLiveData<HomeResponse> = MutableLiveData()
    var errorData: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getHomeData(radius: Int, page: Int) {
        emitLoginData(showProgress = true)
        val limit = page * 15
        val header =
            "Bearer XPFgzKwZGK1yqRxHi0d5xsARFOLpXIvccQj5jekqTnysweGyoIfVUHcH2tPfGq5Oc9kwKHPkcOjk2d1Xobn7aTjOFeop8x41IUfVvg2Y27KiINjYPADcE7Qza0RkX3Yx"
        val url =
            APIEndPoints.LIVE_URL + "v3/businesses/search?radius=$radius&limit=$limit&term=restaurants&latitude=37.786882&longitude=-122.399972&sort_by=distance"

        api.getData(header, url)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.body() != null) {
                    emitLoginData(showProgress = false, rsp = it.body())
                } else {
                    emitLoginData(showProgress = false)
                }
            }, { emitLoginData(showProgress = false, error = it.localizedMessage) })
    }

    private fun emitLoginData(
        showProgress: Boolean? = null, rsp: HomeResponse? = null, error: String? = null,
    ) {
        fetchingData.postValue(showProgress)
        if (rsp != null) homeData.postValue(rsp)
        if (error != null) errorData.postValue(error)
    }
}
