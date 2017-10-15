package com.ckunder.blockchain.model.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ckunder on 10-10-2017.
 */
interface RxSchedulers {
    val io: Scheduler
    val mainThread: Scheduler
    val computation: Scheduler
}