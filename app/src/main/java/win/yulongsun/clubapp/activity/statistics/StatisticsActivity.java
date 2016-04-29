package win.yulongsun.clubapp.activity.statistics;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.activity.statistics
 * USER : yulongsun on 2016/4/13
 * NOTE : 统计
 */
public class StatisticsActivity extends BaseToolbarActivity {
    private Toolbar  tl_statistics;
    private PieChart pieChart_statistics;
    private BarChart barChart_statistics;

    @Override public int getLayoutResId() {
        return R.layout.activity_statistics;
    }

    @Override protected void initView() {
        super.initView();
        tl_statistics = (Toolbar) findViewById(R.id.tl_statistics);
        pieChart_statistics = (PieChart) findViewById(R.id.pieChart_statistics);
        barChart_statistics = (BarChart) findViewById(R.id.barChart_statistics);
    }

    @Override protected void initData() {
        super.initData();
        pieChart_statistics.setDescription("");

        pieChart_statistics.setCenterText(generateCenterText());
        pieChart_statistics.setCenterTextSize(10f);

        // radius of the center hole in percent of maximum radius
        pieChart_statistics.setHoleRadius(45f);
        pieChart_statistics.setTransparentCircleRadius(50f);

        Legend l = pieChart_statistics.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        pieChart_statistics.setData(generatePieData());

        setup(barChart_statistics);
    }

    protected void setup(Chart<?> chart) {

        // no description text
        chart.setDescription("");
        chart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        chart.setTouchEnabled(true);

        if (chart instanceof BarLineChartBase) {

            BarLineChartBase mChart = (BarLineChartBase) chart;

            mChart.setDrawGridBackground(false);

            // enable scaling and dragging
            mChart.setDragEnabled(true);
            mChart.setScaleEnabled(true);

            // if disabled, scaling can be done on x- and y-axis separately
            mChart.setPinchZoom(false);

            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
            leftAxis.setTextSize(8f);
            leftAxis.setTextColor(Color.DKGRAY);
            leftAxis.setValueFormatter(new PercentFormatter());

            XAxis xAxis = mChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextSize(8f);
            xAxis.setTextColor(Color.DKGRAY);

            mChart.getAxisRight().setEnabled(false);
        }

    }

    protected PieData generatePieData() {

        int count = 4;

        ArrayList<Entry>  entries1 = new ArrayList<Entry>();
        ArrayList<String> xVals    = new ArrayList<String>();

        xVals.add("Quarter 1");
        xVals.add("Quarter 2");
        xVals.add("Quarter 3");
        xVals.add("Quarter 4");

        for (int i = 0; i < count; i++) {
            xVals.add("entry" + (i + 1));

            entries1.add(new Entry((float) (Math.random() * 60) + 40, i));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Quarterly Revenues 2015");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(xVals, ds1);

        return d;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

    @Override protected String getToolbarTitle() {
        return "统计";
    }

    @Override protected Toolbar getToolbarLayout() {
        return tl_statistics;
    }
}
