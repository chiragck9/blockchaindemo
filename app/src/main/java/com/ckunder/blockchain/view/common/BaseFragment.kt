package com.ckunder.blockchain.view.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ckunder.blockchain.di.Injectable

/**
 * Created by ckunder on 09-10-2017.
 */
abstract class BaseFragment : Fragment(), Injectable {

    protected abstract fun create(savedInstanceState: Bundle?)

    protected abstract fun createView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View

    protected abstract fun activityCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        create(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityCreated(savedInstanceState)
    }

}