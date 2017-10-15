package com.ckunder.blockchain.model.api.blockchain.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.TypeConverters
import com.ckunder.blockchain.model.local.room.converters.ValueConverter

/**
 * Created by ckunder on 09-10-2017.
 */

@Entity(tableName = "charts_entity")
@TypeConverters(ValueConverter::class)
class ChartsEntity {

    var values: List<ValueEntity>? = null

}
