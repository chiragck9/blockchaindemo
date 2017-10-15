package com.ckunder.blockchain.testutils

import com.ckunder.blockchain.model.api.blockchain.data.Charts
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.api.blockchain.data.Value
import com.ckunder.blockchain.model.api.blockchain.data.ValueEntity

/**
 * Created by ckunder on 12-10-2017.
 */

class DataUtil {

    companion object {

        fun getChartsEntity(): ChartsEntity {
            var chartsEntity = ChartsEntity()
            chartsEntity.values = listOf(ValueEntity(), ValueEntity())
            chartsEntity.values!![0].x = 1504553940L
            chartsEntity.values!![0].y = 4.00f
            chartsEntity.values!![1].x = 1504555920L
            chartsEntity.values!![1].y = 3.03f
            return chartsEntity
        }

        fun getCharts(): Charts {
            var charts = Charts()
            charts.status = "ok"
            charts.name = "Transaction Rate"
            charts.unit = "Transactions Per Second"
            charts.period = "minute"
            charts.description = "The number of Bitcoin transactions added to the mempool per second."
            charts.values = listOf(Value(), Value())
            charts.values!![0].x = 1504553940L
            charts.values!![0].y = 4.00f
            charts.values!![1].x = 1504555920L
            charts.values!![1].y = 3.03f
            return charts
        }
    }

}
