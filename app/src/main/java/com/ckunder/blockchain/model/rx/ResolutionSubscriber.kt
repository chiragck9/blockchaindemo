package com.ckunder.blockchain.model.rx

import com.ckunder.blockchain.model.errorhandling.Resolution
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException

/**
 * Created by ckunder on 10-10-2017.
 */
class ResolutionSubscriber<T>(
        val onNextFunc: (T) -> Unit,
        val onCompletedFunc: () -> Unit = {},
        val onErrorFunc: (Throwable?) -> Unit = {},
        val resolution: Resolution) : ResourceSubscriber<T>() {

    override fun onComplete() {
        onCompletedFunc()
    }

    override fun onError(t: Throwable?) {
        when (t) {
            is retrofit2.adapter.rxjava.HttpException -> resolution.onHttpException(t)
            else -> t?.apply { resolution.onGenericException(this) }
        }

        onErrorFunc(t)
    }


    override fun onNext(t: T) {
        onNextFunc(t)
    }


}