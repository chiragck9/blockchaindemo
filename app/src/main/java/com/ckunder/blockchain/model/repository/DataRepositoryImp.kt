package com.ckunder.blockchain.model.repository

import com.ckunder.blockchain.model.api.ApiDataSource
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.local.LocalDataSource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ckunder on 11-10-2017.
 */
@Singleton
data class DataRepositoryImp @Inject constructor(private val apiDataSource: ApiDataSource, private val localDataSource: LocalDataSource) : DataRepository {

    override fun getCharts(timespan: String, rollingAverage: String, start: String): Flowable<ChartsEntity> {
        //Currently no check for local data
        return apiDataSource.getCharts(timespan, rollingAverage, start)
    }
}