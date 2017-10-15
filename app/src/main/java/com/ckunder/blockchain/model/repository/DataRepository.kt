package com.ckunder.blockchain.model.repository

import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import io.reactivex.Flowable

/**
 * Created by ckunder on 09-10-2017.
 */

interface DataRepository {

    fun getCharts(timespan: String, rollingAverage: String, start: String): Flowable<ChartsEntity>
}