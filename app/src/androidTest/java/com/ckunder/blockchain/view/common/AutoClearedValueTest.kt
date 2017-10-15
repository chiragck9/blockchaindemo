package com.ckunder.blockchain.view.common

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment

import com.ckunder.blockchain.view.utils.AutoClearedValue

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

import kotlin.jvm.*

@RunWith(AndroidJUnit4::class)
class AutoClearedValueTest {


    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java,true,true)

    private var testFragment: TestFragment? = null

    @Before
    fun init() {
        testFragment = TestFragment()
        activityRule.activity.setFragment(testFragment!!)
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    @Test
    fun testClear() {
        testFragment!!.value = AutoClearedValue(testFragment!!, "testFragment")
        activityRule.activity.replaceFragment(TestFragment())
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        assertThat<String>(testFragment!!.value!!.get(), nullValue())
    }

    @Test
    fun testClearOnDestroyed() {
        testFragment!!.value = AutoClearedValue(testFragment!!, "testFragment", onNextFunc = ({}))
        activityRule.activity.replaceFragment(TestFragment())
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        assertThat<String>(testFragment!!.value!!.get(), nullValue())
    }

    @Test
    fun testClearForChildFragment() {
        testFragment!!.value = AutoClearedValue(testFragment!!, "foo")
        testFragment!!.childFragmentManager.beginTransaction()
                .add(Fragment(), "foo").commit()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        assertThat<String>(testFragment!!.value!!.get(), `is`("foo"))
    }


    class TestFragment : Fragment() {

        internal var value: AutoClearedValue<String>? = null
    }
}