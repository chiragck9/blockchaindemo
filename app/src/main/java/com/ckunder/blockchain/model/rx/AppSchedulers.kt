package com.ckunder.blockchain.model.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ckunder on 10-10-2017.
 */
@Singleton
class AppSchedulers @Inject constructor() : RxSchedulers {

    override val io: Scheduler
        get() = Schedulers.io()
    override val mainThread: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val computation: Scheduler
        get() = Schedulers.computation()

}