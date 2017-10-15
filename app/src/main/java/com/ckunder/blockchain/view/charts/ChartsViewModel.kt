package com.ckunder.blockchain.view.charts

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.repository.DataRepository
import com.ckunder.blockchain.model.rx.RxSchedulers
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by ckunder on 09-10-2017.
 */
 open class ChartsViewModel @Inject constructor(val dataRepository: DataRepository, val rxSchedulers: RxSchedulers) : ViewModel() {

   open fun getCharts(timespan: String, rollingAverage: String, start: String): Flowable<ChartsEntity> {
        return dataRepository.getCharts(timespan, rollingAverage, start)
                .subscribeOn(rxSchedulers.io)
                .observeOn(rxSchedulers.mainThread)
    }
}