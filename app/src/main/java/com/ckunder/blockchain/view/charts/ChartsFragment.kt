package com.ckunder.blockchain.view.charts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ckunder.blockchain.R
import com.ckunder.blockchain.databinding.FragmentChartsBinding
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity
import com.ckunder.blockchain.model.rx.ResolutionSubscriber
import com.ckunder.blockchain.view.common.BaseFragment
import com.ckunder.blockchain.view.resolution.LogResolution
import com.ckunder.blockchain.view.utils.AutoClearedValue
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by ckunder on 09-10-2017.
 */
class ChartsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var logResolution: LogResolution
    private lateinit var binding: AutoClearedValue<FragmentChartsBinding>
    private lateinit var chartsViewModel: ChartsViewModel
    private lateinit var disposables: AutoClearedValue<CompositeDisposable>

    override fun create(savedInstanceState: Bundle?) {
        disposables = AutoClearedValue(this, CompositeDisposable(), onNextFunc = ({ disposable -> disposable.clear() }))
    }

    override fun createView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var fragmentChartsBinding = DataBindingUtil.inflate<FragmentChartsBinding>(inflater, R.layout.fragment_charts, container, false)
        binding = AutoClearedValue(this, fragmentChartsBinding)
        return fragmentChartsBinding.root
    }

    override fun activityCreated(savedInstanceState: Bundle?) {

        chartsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChartsViewModel::class.java!!)

        //Can be replaced with LiveData
        binding.get()!!.loading = true;

        disposables.get()!!.add(chartsViewModel
                .getCharts("1weeks", "8hour", "2017-01-01")
                .subscribeWith(ResolutionSubscriber<ChartsEntity>(
                        onNextFunc = ({ chartsEntity ->
                            setUpChart(chartsEntity)
                            binding.get()!!.loading = false;
                        }),
                        onCompletedFunc = ({
                        }),
                        onErrorFunc = ({
                            binding.get()!!.loading = false;
                        }),
                        resolution = logResolution)))
    }

    private fun setUpChart(chart: ChartsEntity) {
        binding.get()!!.setChartEntity(chart)
    }

    companion object {
        fun createInstance(): ChartsFragment {
            val chartsFragment = ChartsFragment()
            return chartsFragment
        }
    }
}

