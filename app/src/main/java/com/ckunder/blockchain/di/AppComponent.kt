package com.ckunder.blockchain.di

import android.app.Application
import com.ckunder.blockchain.BlockchainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule


/**
 * Created by ckunder on 09-10-2017.
 */

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class, AppModule::class, UiModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BlockchainApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: BlockchainApplication)
}