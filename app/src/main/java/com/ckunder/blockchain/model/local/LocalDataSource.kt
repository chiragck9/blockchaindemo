package com.ckunder.blockchain.model.local

import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import io.reactivex.Flowable

/**
 * Created by ckunder on 11-10-2017.
 */
interface LocalDataSource {
    fun getCharts(timespan: String, rollingAverage: String, start: String): Flowable<ChartsEntity>
}