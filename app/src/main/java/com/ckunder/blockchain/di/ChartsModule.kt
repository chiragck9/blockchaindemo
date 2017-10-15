package com.ckunder.blockchain.di

import android.arch.lifecycle.ViewModel
import com.ckunder.blockchain.view.charts.ChartsFragment
import com.ckunder.blockchain.view.charts.ChartsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by ckunder on 12-10-2017.
 */
@Module
internal abstract class ChartsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChartsViewModel::class)
    abstract fun bindChartsViewModel(viewModel: ChartsViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeChartsFragment(): ChartsFragment

}