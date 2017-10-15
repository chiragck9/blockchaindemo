package com.ckunder.blockchain.model.local.room

import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.local.LocalDataSource
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ckunder on 11-10-2017.
 */
@Singleton
class RoomDataSource @Inject constructor() : LocalDataSource {

    override fun getCharts(timespan: String, rollingAverage: String, start: String): Flowable<ChartsEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}