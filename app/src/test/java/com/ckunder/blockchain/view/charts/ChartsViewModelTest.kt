package com.ckunder.blockchain.view.charts

import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.repository.DataRepository
import com.ckunder.blockchain.model.rx.MockSchedulers
import com.ckunder.blockchain.testutils.*
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner


/**
 * Created by ckunder on 12-10-2017.
 */
@RunWith(PowerMockRunner::class)
class ChartsViewModelTest {

    lateinit var classToTest: ChartsViewModel
    @Mock lateinit var dataRepository: DataRepository
    val mockSchedulers = MockSchedulers()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        classToTest = ChartsViewModel(dataRepository, mockSchedulers)
    }

    @Test
    fun getChartsTest() {

        var chartsEntity = DataUtil.getChartsEntity()
        given(dataRepository.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.just(chartsEntity))

        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe()

        verify(dataRepository, once()).getCharts("8weeks", "8hours", "2017-01-01")
    }

    @Test
    fun getChartsSuccessfulResponse() {

        var chartsEntity = DataUtil.getChartsEntity()
        given(dataRepository.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.just(chartsEntity))

        val testSubscriber = TestSubscriber<ChartsEntity>()
        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe(testSubscriber)

        testSubscriber.assertNoErrors().assertValue(chartsEntity).assertComplete()
    }

    @Test
    fun getChartsError() {

        var exception = Exception("someException")
        given(dataRepository.getCharts("8weeks", "8hours", "2017-01-01")).willReturn(Flowable.error(exception))

        val testSubscriber = TestSubscriber<ChartsEntity>()
        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribe(testSubscriber)

        testSubscriber.assertNoValues().assertError({ exception -> exception.message.equals("someException") })
    }

}
