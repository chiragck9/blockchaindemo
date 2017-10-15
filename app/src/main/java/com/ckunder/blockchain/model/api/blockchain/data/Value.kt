package com.ckunder.blockchain.model.api.blockchain.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by ckunder on 09-10-2017.
 */

class Value {

    @SerializedName("x")
    @Expose
    var x: Long? = null
    @SerializedName("y")
    @Expose
    var y: Float? = null

}