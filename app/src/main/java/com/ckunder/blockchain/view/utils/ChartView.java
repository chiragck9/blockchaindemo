package com.ckunder.blockchain.view.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.ckunder.blockchain.model.api.blockchain.data.ChartsEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by ckunder on 11-10-2017.
 */

public class ChartView extends LineChartView {

    private ChartsEntity chartsEntity;

    private boolean hasAxes = true;
    private boolean hasAxesNames = false;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setChartsEntity(ChartsEntity chartsEntity) {
        this.chartsEntity = chartsEntity;
        if (chartsEntity != null) {
            setUpChart();
        }
    }

    private void setUpChart() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" MMM d");

        List<AxisValue> xAxisLabels = new ArrayList<>();
        List<AxisValue> yAxisLabels = new ArrayList<>();
        List<PointValue> values = new ArrayList<>();
        int size = chartsEntity.getValues().size();
        for (int j = 0; j < size; ++j) {
            if (j % 10 == 0) {
                values.add(new PointValue(chartsEntity.getValues().get(j).getX(), chartsEntity.getValues().get(j).getY()));
                Date d = new Date(chartsEntity.getValues().get(j).getX() * 1000);
                xAxisLabels.add(new AxisValue(j).setLabel(simpleDateFormat.format(d)));

                yAxisLabels.add(new AxisValue(j).setLabel("1K"));
            }
        }

        List<Line> lines = new ArrayList<Line>();
        Line line = new Line(values);
        line.setColor(Color.BLUE);
        line.setShape(shape);
        line.setCubic(isCubic);
        line.setFilled(isFilled);
        line.setHasLabels(hasLabels);
        line.setHasLabelsOnlyForSelected(hasLabelForSelected);
        line.setHasLines(hasLines);
        line.setHasPoints(hasPoints);
        lines.add(line);

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis(xAxisLabels);
            Axis axisY = new Axis(yAxisLabels).setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        setLineChartData(data);
    }

}
