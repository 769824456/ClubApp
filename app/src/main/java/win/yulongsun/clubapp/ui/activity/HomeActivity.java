package win.yulongsun.clubapp.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.Bind;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.ui.activity.daily.DailyActivity;
import win.yulongsun.clubapp.ui.activity.member.MemberActivity;
import win.yulongsun.clubapp.ui.activity.mine.MineActivity;
import win.yulongsun.clubapp.ui.activity.statistics.StatisticsActivity;
import win.yulongsun.clubapp.ui.activity.user.UserActivity;
import win.yulongsun.clubapp.utils.LocalImageHolderView;
import win.yulongsun.yulongsunutils.common.BaseActivity;

/**
 * 主页
 */
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    @Bind(R.id.tl_main)     Toolbar          mTlMain;
    @Bind(R.id.cb_home)     ConvenientBanner mCbHome;
    @Bind(R.id.nav_view)    NavigationView   mNavView;
    @Bind(R.id.drawer_home) DrawerLayout     mDrawerHome;
    LinearLayout mLlNavHeader;
    ImageView    mIvNavHeaderAvatar;
    ImageView    mIvNavHeaderLogout;

    private ArrayList<Integer> mLocalImages;

    @Override public int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override protected void initViews() {
        super.initViews();
        setSupportActionBar(mTlMain);
        getSupportActionBar().setTitle("主页");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerHome, mTlMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerHome.setDrawerListener(toggle);
        toggle.syncState();
        //findViews
        View headerView = mNavView.getHeaderView(0);
        mLlNavHeader = (LinearLayout) headerView.findViewById(R.id.ll_nav_header);
        mIvNavHeaderAvatar = (ImageView) headerView.findViewById(R.id.iv_nav_header_avatar);
        mIvNavHeaderLogout = (ImageView) headerView.findViewById(R.id.iv_nav_header_logout);
    }

    @Override protected void initListeners() {
        super.initListeners();
        mNavView.setNavigationItemSelectedListener(this);
        mLlNavHeader.setOnClickListener(this);
        mIvNavHeaderLogout.setOnClickListener(this);

    }


    @Override protected void initDatas() {
        super.initDatas();
        //本地图片集合
        mLocalImages = new ArrayList<>();
        for (int position = 1; position < 5; position++)
            mLocalImages.add(getResId("bg_home_" + position, R.mipmap.class));

        mCbHome.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, mLocalImages)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int    id      = item.getItemId();
        Intent mIntent = null;
        if (id == R.id.nav_member) {
            mIntent = new Intent(HomeActivity.this, MemberActivity.class);
        } else if (id == R.id.nav_daily) {
            mIntent = new Intent(HomeActivity.this, DailyActivity.class);
        } else if (id == R.id.nav_statistics) {
            mIntent = new Intent(HomeActivity.this, StatisticsActivity.class);
        } else if (id == R.id.nav_user) {
            mIntent = new Intent(HomeActivity.this, UserActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        drawer.closeDrawer(GravityCompat.START);


        if (mIntent != null) {
            startActivity(mIntent);
        }
        return true;
    }


    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.iv_nav_header_logout:
                break;
            case R.id.ll_nav_header:
                intent = new Intent(HomeActivity.this, MineActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
