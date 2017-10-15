package com.ckunder.blockchain.model.errorhandling

import retrofit2.adapter.rxjava.HttpException

/**
 * Created by ckunder on 10-10-2017.
 */

interface RxHttpException {

    fun onGenericException(t: Throwable?)
    fun onHttpException(httpException: HttpException)
}

interface Resolution : RxHttpException {
}