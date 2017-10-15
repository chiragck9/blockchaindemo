package com.ckunder.blockchain.model.api

import com.ckunder.blockchain.model.api.blockchain.BlockChainApiSource
import com.ckunder.blockchain.model.api.blockchain.BlockChainService
import com.ckunder.blockchain.model.api.blockchain.data.Charts
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.api.blockchain.data.ValueEntity
import com.ckunder.blockchain.model.local.LocalDataSource
import com.ckunder.blockchain.model.repository.DataRepository
import com.ckunder.blockchain.model.repository.DataRepositoryImp
import com.ckunder.blockchain.testutils.DataUtil.Companion.getCharts
import com.ckunder.blockchain.testutils.DataUtil.Companion.getChartsEntity
import com.ckunder.blockchain.testutils.given
import com.ckunder.blockchain.testutils.once
import com.ckunder.blockchain.testutils.verify
import io.reactivex.Flowable
import io.reactivex.functions.Predicate
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner

/**
 * Created by ckunder on 12-10-2017.
 */
@RunWith(PowerMockRunner::class)
class ApiDataSourceTest {

    lateinit var classToTest: ApiDataSource
    @Mock lateinit var blockChainService: BlockChainService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        classToTest = BlockChainApiSource(blockChainService)
    }

    @Test
    fun getChartsTest() {

        //Given
        var charts = getCharts()
        given(blockChainService.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.just(charts))

        //Test
        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe()

        //Verify
        verify(blockChainService, once()).getCharts("8weeks", "8hours", "2017-01-01")
    }

    @Test
    fun getChartsSuccessfulResponse() {

        //Given
        var charts = getCharts()
        given(blockChainService.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.just(charts))

        //Test
        val testSubscriber = TestSubscriber<ChartsEntity>()
        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe(testSubscriber)

        //Verify mapped values
        testSubscriber
                .assertNoErrors()
                .assertValueAt(0, { chartsEntity -> chartsEntity.values!![0].x == charts.values!![0].x })
                .assertValueAt(0, { chartsEntity -> chartsEntity.values!![0].y == charts.values!![0].y })
                .assertComplete()
    }

    @Test
    fun getChartsError() {
        //Given
        var exception = Exception("someException")
        given(blockChainService.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.error(exception))

        //Test
        val testSubscriber = TestSubscriber<ChartsEntity>()
        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe(testSubscriber)

        //Verify
        testSubscriber.assertError(exception)
    }

}