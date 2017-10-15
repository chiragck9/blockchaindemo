package com.ckunder.blockchain.view.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.ckunder.blockchain.R
import com.ckunder.blockchain.view.MainActivity
import com.ckunder.blockchain.view.charts.ChartsFragment
import javax.inject.Inject

/**
 * Created by ckunder on 10-10-2017.
 */

class NavigationController @Inject constructor(baseActivity: MainActivity) {
    private var fragmentManager: FragmentManager = baseActivity.supportFragmentManager

    fun navigateToChartsFragment() {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(R.id.container, ChartsFragment.createInstance(), ChartsFragment::class.java!!.getSimpleName())
                .commit()
    }
}
