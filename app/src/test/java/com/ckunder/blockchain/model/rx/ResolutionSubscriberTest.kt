package com.ckunder.blockchain.model.rx

import com.ckunder.blockchain.model.api.blockchain.BlockChainService
import com.ckunder.blockchain.model.errorhandling.Resolution
import com.ckunder.blockchain.testutils.mock
import com.ckunder.blockchain.testutils.verify
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Response
import retrofit2.adapter.rxjava.HttpException

/**
 * Created by ckunder on 12-10-2017.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(HttpException::class)
class ResolutionSubscriberTest {

    @Mock lateinit var resolution: Resolution
    @Mock lateinit var obj: Throwable
    @Mock lateinit var genericException: Throwable
    @Mock lateinit var httpException: HttpException

    var onNextFunc: (Throwable) -> Unit = {
        it.printStackTrace()
    }

    var onCompletedFunc: () -> Unit = {

    }

    lateinit var classToTest: ResolutionSubscriber<Throwable>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        classToTest = ResolutionSubscriber(onNextFunc, onCompletedFunc, resolution = resolution)
    }

    @Test
    fun onNext() {
        classToTest.onNext(obj)

        verify(obj).printStackTrace()
    }

    @Test
    fun httpException() {

        classToTest.onError(httpException)

        verify(resolution).onHttpException(httpException)
    }

    @Test
    fun genericException() {

        classToTest.onError(genericException)

        verify(resolution).onGenericException(genericException)
    }

}