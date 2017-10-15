package com.ckunder.blockchain.view.resolution

import android.util.Log
import retrofit2.adapter.rxjava.HttpException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ckunder on 11-10-2017.
 */
@Singleton
class LogResolution @Inject constructor() : ResolutionProvider() {

    companion object {
        val TAG: String = "NETWORK_ERROR_TAG"
    }

    override fun onInternalServerError(httpException: HttpException) {
        Log.i(TAG, "onInternalServerError ----> " + httpException)
    }

    override fun onServiceMaintainance(httpException: HttpException) {
        Log.i(TAG, "onServiceMaintainance ----> " + httpException)
    }

    override fun onNotFound(httpException: HttpException) {
        Log.i(TAG, "onNotFound ----> " + httpException)
    }

    override fun onGenericException(t: Throwable?) {
        Log.i(TAG, "onGenericException ----> " + t)
    }

}