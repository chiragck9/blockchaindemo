package com.ckunder.blockchain.di

/**
 * Created by ckunder on 12-10-2017.
 */
import android.arch.lifecycle.ViewModelProvider
import com.ckunder.blockchain.view.MainActivity
import com.ckunder.blockchain.view.common.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = arrayOf(ChartsModule::class))
    internal abstract fun contributeMainActivity(): MainActivity

}