package com.ckunder.blockchain.view.resolution

import com.ckunder.blockchain.model.errorhandling.Resolution
import retrofit2.adapter.rxjava.HttpException

/**
 * Created by ckunder on 12-10-2017.
 */
abstract class ResolutionProvider : Resolution {

    override fun onHttpException(httpException: HttpException) {

        when (httpException.code()) {
            500 -> onInternalServerError(httpException)
            503 -> onServiceMaintainance(httpException)
            404 -> onNotFound(httpException)
            else -> onInternalServerError(httpException)
        }
    }

    abstract fun onInternalServerError(httpException: HttpException)
    abstract fun onServiceMaintainance(httpException: HttpException)
    abstract fun onNotFound(httpException: HttpException)
}