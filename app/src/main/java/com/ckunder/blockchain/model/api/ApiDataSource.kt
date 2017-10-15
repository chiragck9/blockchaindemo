package com.ckunder.blockchain.model.api

import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import io.reactivex.Flowable
import retrofit2.http.Query

/**
 * Created by ckunder on 11-10-2017.
 */
interface ApiDataSource{

    fun getCharts(timespan: String, rollingAverage: String, start: String): Flowable<ChartsEntity>
}