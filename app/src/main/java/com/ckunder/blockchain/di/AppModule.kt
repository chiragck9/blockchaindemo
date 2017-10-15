package com.ckunder.blockchain.di

import com.ckunder.blockchain.model.api.ApiDataSource
import com.ckunder.blockchain.model.api.blockchain.BlockChainApiSource
import com.ckunder.blockchain.model.api.blockchain.BlockChainService
import com.ckunder.blockchain.model.local.LocalDataSource
import com.ckunder.blockchain.model.local.room.RoomDataSource
import com.ckunder.blockchain.model.local.room.RoomDataSource_Factory
import com.ckunder.blockchain.model.repository.DataRepository
import com.ckunder.blockchain.model.repository.DataRepositoryImp
import com.ckunder.blockchain.model.rx.AppSchedulers
import com.ckunder.blockchain.model.rx.RxSchedulers
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by ckunder on 09-10-2017.
 */
@Module(includes = arrayOf(UiModule::class))
class AppModule {
    companion object {
        val BASE_URL: String = "https://api.blockchain.info/"
    }

    @Provides
    @Singleton
    fun blockChainService(): BlockChainService {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(BlockChainService::class.java);
    }

    @Provides
    @Singleton
    fun apiDataSource(blockChainService: BlockChainService): ApiDataSource {
        return BlockChainApiSource(blockChainService)
    }

    @Provides
    @Singleton
    fun localDataSource(): LocalDataSource {
        return RoomDataSource()
    }

    @Provides
    @Singleton
    fun dataRepository(apiDataSource: ApiDataSource, localDataSource: LocalDataSource): DataRepository {
        return DataRepositoryImp(apiDataSource, localDataSource)
    }


    @Provides
    @Singleton
    fun schedulers(): RxSchedulers {
        return AppSchedulers()
    }
}