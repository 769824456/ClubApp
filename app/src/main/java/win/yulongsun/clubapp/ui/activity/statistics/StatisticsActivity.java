package win.yulongsun.clubapp.ui.activity.statistics;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity.statistics
 * USER : yulongsun on 2016/4/13
 * NOTE : 统计
 */
public class StatisticsActivity extends BaseToolbarActivity {
    private static final String TAG = StatisticsActivity.class.getSimpleName();
    @Bind(R.id.tl_statistics)   Toolbar                    mTlStatistics;
    @Bind(R.id.tabl_statistics) TabLayout                  mTablStatistics;
    @Bind(R.id.vp_statistics)   ViewPager                  mVpStatistics;
    private                     ArrayList<Fragment>        mFragmentLists;
    private                     SimpleFragmentPagerAdapter mPagerAdapter;

    @Override public int getLayoutResId() {
        return R.layout.activity_statistics;
    }

    @Override protected int getMenuResId() {
        return 0;
    }

    @Override protected String getToolbarTitle() {
        return "统计";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlStatistics;
    }

    @Override protected void initViews() {
        super.initViews();
        mFragmentLists = new ArrayList<Fragment>();
        mFragmentLists.add(new DayFragment());
        mFragmentLists.add(new MonthFragment());
        mFragmentLists.add(new QuarterFragment());
        mPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        mVpStatistics.setAdapter(mPagerAdapter);
        mTablStatistics.setupWithViewPager(mVpStatistics);
    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        final   int    PAGE_COUNT  = 3;
        private String tabTitles[] = new String[]{"今日销售额", "当月销售额", "当季销售额"};

        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentLists.get(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}
