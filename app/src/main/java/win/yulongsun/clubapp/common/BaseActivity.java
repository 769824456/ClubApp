package win.yulongsun.clubapp.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.activity
 * USER : yulongsun on 2016/4/11
 * NOTE :
 */
public abstract class BaseActivity extends AppCompatActivity {

    private int         layoutResId;
    private ProgressBar mProgressBar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutResId();
        setContentView(layoutResId);

        initView();
        initListeners();
        initData();
        mProgressBar = new ProgressBar(this);
    }

    protected void initView() {

    }

    protected void initListeners() {

    }

    protected void initData() {

    }


    /*获取布局*/
    public abstract int getLayoutResId();

    /*显示对话框*/
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);

    }
    /*显示对话框*/
    public void showLoading(String tip) {
        mProgressBar.setContentDescription(tip);
        mProgressBar.setVisibility(View.VISIBLE);

    }

    /*隐藏对话框*/
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
    }
}
