package com.ckunder.blockchain.model.api.blockchain

import com.ckunder.blockchain.model.api.blockchain.data.Charts
import com.ckunder.blockchain.testutils.assertThat
import com.ckunder.blockchain.testutils.isValue
import com.ckunder.blockchain.testutils.notNullValue
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import io.reactivex.subscribers.TestSubscriber
import org.powermock.core.classloader.annotations.PowerMockIgnore


/**
 * Created by ckunder on 12-10-2017.
 */
@RunWith(PowerMockRunner::class)
@PowerMockIgnore("javax.net.ssl.*")
class BlockChainServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var classToTest: BlockChainService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        classToTest = retrofit.create(BlockChainService::class.java)
    }

    @After
    fun stop() {
        mockWebServer.shutdown()
    }

    @Test
    fun getCharts() {
        enqueueResponse("charts.json")

        val testsubscriber = TestSubscriber<Charts>()
        classToTest.getCharts("8weeks", "8hours", "2017-01-01").subscribeWith(testsubscriber)

        val request = mockWebServer.takeRequest()
        assertThat(request.path, isValue("/charts/transactions-per-second?timespan=8weeks&rollingAverage=8hours&start=2017-01-01"))

        testsubscriber.awaitTerminalEvent()

        val charts = testsubscriber.values().get(0) as Charts
        assertThat(charts.status, isValue("ok"))
        assertThat(charts.name, isValue("Transaction Rate"))
        assertThat(charts.unit, isValue("Transactions Per Second"))
        assertThat(charts.period, isValue("minute"))
        assertThat(charts.description, isValue("The number of Bitcoin transactions added to the mempool per second."))
        assertThat(charts.values, notNullValue())
        assertThat(charts.values!!.size, isValue(14))
        assertThat(charts.values!!.get(0).x, isValue(1504553940L))
        assertThat(charts.values!!.get(0).y, isValue(4.00f))
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/" + fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)))
    }
}