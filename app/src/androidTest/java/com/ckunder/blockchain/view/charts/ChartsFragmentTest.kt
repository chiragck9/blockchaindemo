package com.ckunder.blockchain.view.charts

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ckunder.blockchain.R
import com.ckunder.blockchain.model.api.blockchain.BlockChainApiSource
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.api.blockchain.data.ValueEntity
import com.ckunder.blockchain.model.local.room.RoomDataSource
import com.ckunder.blockchain.model.repository.DataRepositoryImp
import com.ckunder.blockchain.view.ViewModelUtil
import com.ckunder.blockchain.view.common.SingleFragmentActivity
import com.ckunder.blockchain.view.resolution.LogResolution
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito.`when` as whenMock
/**
 * Created by ckunder on 12-10-2017.
 */

@RunWith(AndroidJUnit4::class)
class ChartsFragmentTest {

    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Mock
    lateinit var chartsViewModel: ChartsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val chartsFragment = ChartsFragment()
        chartsFragment.logResolution = LogResolution()
        chartsFragment.viewModelFactory = ViewModelUtil.createFor(chartsViewModel)
        activityRule.getActivity().setFragment(chartsFragment)
    }

    @Test
    fun test() {
        var chartsEntity = getChartsEntity()
        whenMock(chartsViewModel.getCharts(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Flowable.just(chartsEntity))

        onView(withId(R.id.chart)).check(matches(isDisplayed()))
        onView(withId(R.id.progress_bar)).check(matches(CoreMatchers.not(isDisplayed())))
    }

    fun getChartsEntity(): ChartsEntity {
        var chartsEntity = ChartsEntity()
        chartsEntity.values = listOf(ValueEntity(), ValueEntity())
        chartsEntity.values!![0].x = 1504553940L
        chartsEntity.values!![0].y = 4.00f
        chartsEntity.values!![1].x = 1504555920L
        chartsEntity.values!![1].y = 3.03f
        return chartsEntity
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.ckunder.blockchain", appContext.packageName)
    }
}