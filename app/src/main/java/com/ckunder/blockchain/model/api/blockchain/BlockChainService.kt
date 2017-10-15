package com.ckunder.blockchain.model.api.blockchain

import com.ckunder.blockchain.model.api.blockchain.data.Charts
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ckunder on 09-10-2017.
 */
interface BlockChainService {
    @GET("charts/transactions-per-second")
    fun getCharts(@Query("timespan") timespan: String, @Query("rollingAverage") rollingAverage: String, @Query("start") start: String): Flowable<Charts>
}