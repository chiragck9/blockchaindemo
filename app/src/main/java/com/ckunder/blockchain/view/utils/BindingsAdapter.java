package com.ckunder.blockchain.view.utils;

import android.databinding.BindingAdapter;
import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity;

/**
 * Created by chira on 19-08-2017.
 */

public class BindingsAdapter {

    @BindingAdapter("chartEntity")
    public static void setChartEntity(ChartView view, ChartsEntity chartsEntity) {
        if (view != null) {
            view.setChartsEntity(chartsEntity);
        }
    }
}
