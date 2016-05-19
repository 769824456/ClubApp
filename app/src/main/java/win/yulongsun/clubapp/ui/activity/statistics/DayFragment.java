package win.yulongsun.clubapp.ui.activity.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.clubapp.net.entity.ChartVo;
import win.yulongsun.clubapp.net.response.ChartVoResponseList;
import win.yulongsun.component.cache.ACache;
import win.yulongsun.utils.utils.GsonUtils;
import win.yulongsun.utils.utils.ToastUtils;

public class DayFragment extends Fragment {

    private static final String TAG = DayFragment.class.getSimpleName();
    @Bind(R.id.lc_statistics_day) LineChart mLcStatisticsDay;
    private                       View      rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_day, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String user_c_id = ACache.get(getActivity()).getAsString(Constants.USER_C_ID);
        OkHttpUtils.post().url(Api.HOST + Api.BIll + "listDay")
                .addParams("user_c_id",user_c_id)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {

                    }

                    @Override public void onResponse(String response) {
                        Log.d(TAG, response);
                        ChartVoResponseList chartVoResponseList = GsonUtils.parseToBean(response, ChartVoResponseList.class);
                        if (chartVoResponseList.error) {
                            ToastUtils.showMessage(getActivity(), "服务器异常");
                        } else {
                            List<ChartVo> chartVoList = chartVoResponseList.result;
                            Log.d(TAG, "onResponse: " + chartVoList.toString());
                            LineData mLineData = getLineData(chartVoList);
                            showChart(mLcStatisticsDay, mLineData);
                            mLcStatisticsDay.animateY(1400, Easing.EasingOption.EaseInOutQuart);
                        }
                    }
                });

    }

    private LineData getLineData(List<ChartVo> chartVoList) {
        List<String> xValues = new ArrayList<String>();
        for (int i = 0; i < chartVoList.size(); i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add("" + chartVoList.get(i).time);
        }

        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < chartVoList.size(); i++) {
            yValues.add(new Entry(chartVoList.get(i).num, i));
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

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
