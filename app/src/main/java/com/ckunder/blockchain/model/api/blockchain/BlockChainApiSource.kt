package com.ckunder.blockchain.model.api.blockchain

import com.ckunder.blockchain.model.api.ApiDataSource
import com.ckunder.blockchain.model.api.blockchain.data.Charts
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.api.blockchain.data.ValueEntity
import io.reactivex.Flowable
import io.reactivex.functions.Function
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ckunder on 11-10-2017.
 */

@Singleton
class BlockChainApiSource @Inject constructor(private val blockChainService: BlockChainService) : ApiDataSource {
    override fun getCharts(timespan: String, rollingAverage: String, start: String): Flowable<ChartsEntity> {
        return blockChainService.getCharts(timespan, rollingAverage, start).map(object : Function<Charts, ChartsEntity> {
            override fun apply(charts: Charts): ChartsEntity {
                val chartsEntity = ChartsEntity()
                charts?.let {
                    val valueEntities = ArrayList<ValueEntity>()
                    for (value in charts.values!!) {
                        val valueEntity = ValueEntity()
                        valueEntity.x = value.x
                        valueEntity.y = value.y
                        valueEntities.add(valueEntity)
                    }
                    chartsEntity.values = valueEntities
                }
                return chartsEntity
            }
        })
    }
}