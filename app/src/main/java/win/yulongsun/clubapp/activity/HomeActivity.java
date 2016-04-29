package win.yulongsun.clubapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.Arrays;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.activity.daily.DailyActivity;
import win.yulongsun.clubapp.activity.member.MemberActivity;
import win.yulongsun.clubapp.activity.statistics.StatisticsActivity;
import win.yulongsun.clubapp.activity.user.UserActivity;
import win.yulongsun.clubapp.common.BaseActivity;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ConvenientBanner     cb_home;
    private Toolbar              tl_main;
    private DrawerLayout         drawer_home;
    private FloatingActionButton fab_home;
    private NavigationView       nav_view;

    @Override public int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override protected void initView() {
        super.initView();
        tl_main = (Toolbar) findViewById(R.id.tl_main);
        cb_home = (ConvenientBanner) findViewById(R.id.cb_home);
        drawer_home = (DrawerLayout) findViewById(R.id.drawer_home);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        fab_home = (FloatingActionButton) findViewById(R.id.fab_home);

        setSupportActionBar(tl_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_home, tl_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_home.setDrawerListener(toggle);
        toggle.syncState();


    }

    @Override protected void initListeners() {
        super.initListeners();
        nav_view.setNavigationItemSelectedListener(this);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    @Override protected void initData() {
        super.initData();
        cb_home.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, Arrays.asList(images))
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
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
     * Created by Sai on 15/8/4.
     * 网络图片加载例子
     */
    public class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            imageView.setImageResource(R.mipmap.ic_launcher);
//            ImageLoader.getInstance().displayImage(data,imageView);
            Glide.with(context).load(data).into(imageView);
        }
    }


}
