package com.ckunder.blockchain.model.repository

import com.ckunder.blockchain.model.api.ApiDataSource
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.api.blockchain.data.ValueEntity
import com.ckunder.blockchain.model.local.LocalDataSource
import com.ckunder.blockchain.testutils.DataUtil.Companion.getChartsEntity
import com.ckunder.blockchain.testutils.given
import com.ckunder.blockchain.testutils.once
import com.ckunder.blockchain.testutils.verify
import com.ckunder.blockchain.testutils.verifyNoMoreInteractions
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.HttpException

/**
 * Created by ckunder on 12-10-2017.
 */
@RunWith(PowerMockRunner::class)
class DataRepositoryTest {

    lateinit var classToTest: DataRepository
    @Mock lateinit var apiDataSource: ApiDataSource
    @Mock lateinit var localDataSource: LocalDataSource
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiDataSource = mock(ApiDataSource::class.java)
        localDataSource = mock(LocalDataSource::class.java)
        classToTest = DataRepositoryImp(apiDataSource, localDataSource)
    }

    @Test
    fun getChartsTest() {

        var chartsEntity = getChartsEntity()
        given(apiDataSource.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.just(chartsEntity))

        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe()

        verify(apiDataSource, once()).getCharts("8weeks", "8hours", "2017-01-01")
        verify(localDataSource, never()).getCharts("8weeks", "8hours", "2017-01-01")
    }

    @Test
    fun getChartsSuccessfulResponse() {

        var chartsEntity = getChartsEntity()
        given(apiDataSource.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.just(chartsEntity))

        val testSubscriber = TestSubscriber<ChartsEntity>()
        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe(testSubscriber)

        testSubscriber.assertNoErrors().assertValue(chartsEntity).assertComplete()
    }

    @Test
    fun getChartsError() {

        var exception = Exception("someException")
        given(apiDataSource.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.error(exception))

        val testSubscriber = TestSubscriber<ChartsEntity>()
        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe(testSubscriber)

        testSubscriber.assertError(exception)
    }

}