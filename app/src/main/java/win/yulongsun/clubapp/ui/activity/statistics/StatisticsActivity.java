package win.yulongsun.clubapp.ui.activity.statistics;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.statistics
 * USER : yulongsun on 2016/4/13
 * NOTE : 统计
 */
public class StatisticsActivity extends BaseToolbarActivity {
    @Bind(R.id.tl_statistics)   Toolbar              mTlStatistics;
    @Bind(R.id.lc_statistics)   LineChart            mLcStatistics;

    @Override public int getLayoutResId() {
        return R.layout.activity_statistics;
    }

    @Override protected void initViews() {
        super.initViews();


        LineData mLineData = getLineData(24, 10);
        showChart(mLcStatistics, mLineData);
        mLcStatistics.animateY(1400, Easing.EasingOption.EaseInOutQuart);
    }


    // 设置显示的样式
    private void showChart(LineChart lineChart, LineData lineData) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("今日还没有营业数据");
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度
        lineChart.setTouchEnabled(false); // 设置是否可以触摸
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放
        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true);//
        lineChart.setBackgroundColor(Color.WHITE);// 设置背景
        // add data
        lineChart.setData(lineData); // 设置数据

        XAxis mXAxis = lineChart.getXAxis();
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis axisRight = lineChart.getAxisRight();
        axisRight.setEnabled(false);
        axisRight.setDrawTopYLabelEntry(true);
        //Legend 图例
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        mLegend.setForm(Legend.LegendForm.LINE);// 样式
        mLegend.setFormSize(15f);// 字体
        mLegend.setTextColor(Color.BLACK);// 颜色

        lineChart.animateX(2000); // 立即执行的动画,x轴
    }

    /**
     * 生成一个数据
     * @param count 表示图表中有多少个坐标点
     * @param range 用来生成range以内的随机数
     * @return
     */
    private LineData getLineData(int count, float range) {
        List<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add("" + i);
        }

        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < 9; i++) {
            int value = (int) (Math.random() * range);
            yValues.add(new Entry(value, i));
        }

        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "今日销售额");

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(3f);// 显示的圆形大小
        lineDataSet.setColor(Color.RED);// 显示颜色
        lineDataSet.setCircleColor(Color.RED);// 圆形的颜色
        lineDataSet.setValueFormatter(new LargeValueFormatter());
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setHighLightColor(Color.RED); // 高亮的线的颜色

        List<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        LineData lineData = new LineData(xValues, lineDataSets);

        return lineData;
    }

//    @Override protected void initDatas() {
//        super.initDatas();
//        pieChart_statistics.setDescription("");
//
//        pieChart_statistics.setCenterText(generateCenterText());
//        pieChart_statistics.setCenterTextSize(10f);
//
//        // radius of the center hole in percent of maximum radius
//        pieChart_statistics.setHoleRadius(45f);
//        pieChart_statistics.setTransparentCircleRadius(50f);
//
//        Legend l = pieChart_statistics.getLegend();
//        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//
//        pieChart_statistics.setData(generatePieData());
//
//        setup(barChart_statistics);
//    }
//
//    protected void setup(Chart<?> chart) {
//
//        // no description text
//        chart.setDescription("");
//        chart.setNoDataTextDescription("You need to provide data for the chart.");
//
//        // enable touch gestures
//        chart.setTouchEnabled(true);
//
//        if (chart instanceof BarLineChartBase) {
//
//            BarLineChartBase mChart = (BarLineChartBase) chart;
//
//            mChart.setDrawGridBackground(false);
//
//            // enable scaling and dragging
//            mChart.setDragEnabled(true);
//            mChart.setScaleEnabled(true);
//
//            // if disabled, scaling can be done on x- and y-axis separately
//            mChart.setPinchZoom(false);
//
//            YAxis leftAxis = mChart.getAxisLeft();
//            leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//            leftAxis.setTextSize(8f);
//            leftAxis.setTextColor(Color.DKGRAY);
//            leftAxis.setValueFormatter(new PercentFormatter());
//
//            XAxis xAxis = mChart.getXAxis();
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            xAxis.setTextSize(8f);
//            xAxis.setTextColor(Color.DKGRAY);
//
//            mChart.getAxisRight().setEnabled(false);
//        }
//
//    }
//
//    protected PieData generatePieData() {
//
//        int count = 4;
//
//        ArrayList<Entry>  entries1 = new ArrayList<Entry>();
//        ArrayList<String> xVals    = new ArrayList<String>();
//
//        xVals.add("Quarter 1");
//        xVals.add("Quarter 2");
//        xVals.add("Quarter 3");
//        xVals.add("Quarter 4");
//
//        for (int i = 0; i < count; i++) {
//            xVals.add("entry" + (i + 1));
//
//            entries1.add(new Entry((float) (Math.random() * 60) + 40, i));
//        }
//
//        PieDataSet ds1 = new PieDataSet(entries1, "Quarterly Revenues 2015");
//        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
//        ds1.setSliceSpace(2f);
//        ds1.setValueTextColor(Color.WHITE);
//        ds1.setValueTextSize(12f);
//
//        PieData d = new PieData(xVals, ds1);
//
//        return d;
//    }
//
//    private SpannableString generateCenterText() {
//        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
//        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
//        return s;
//    }

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "统计";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlStatistics;
    }

}
