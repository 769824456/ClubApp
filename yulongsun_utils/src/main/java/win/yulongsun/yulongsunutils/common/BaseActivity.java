package win.yulongsun.yulongsunutils.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.ButterKnife;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity
 * USER : yulongsun on 2016/4/11
 * NOTE :
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private ProgressBar mProgressBar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutResId();
        setContentView(layoutResId);
        ButterKnife.bind(this);
        initViews();
        initListeners();
        initDatas();
        mProgressBar = new ProgressBar(this);
    }

    protected void initViews() {

    }

    protected void initListeners() {

    }

    protected void initDatas() {

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


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");

    }

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
