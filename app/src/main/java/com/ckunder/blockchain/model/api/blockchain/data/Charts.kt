package com.ckunder.blockchain.model.api.blockchain.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by ckunder on 09-10-2017.
 */

class Charts {

    @SerializedName("status")
    @Expose
    var status: String = ""
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("unit")
    @Expose
    var unit: String? = null
    @SerializedName("period")
    @Expose
    var period: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("values")
    @Expose
    var values: List<Value>? = null

}
