package win.yulongsun.yulongsunutils.common;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import win.yulongsun.yulongsunutils.R;


/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.activity
 * USER : yulongsun on 2016/4/13
 * NOTE :
 */
public abstract class BaseToolbarActivity extends BaseActivity implements IBaseView {

    private ProgressDialog mProgDialog;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置Toolbar
        Toolbar toolbar = getToolbarLayout();
        if (toolbar != null) {
            this.setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getToolbarTitle());
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow_left);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuResId = getMenuResId();
        if (menuResId != 0) {
            getMenuInflater().inflate(menuResId, menu);
        }
        return true;
    }

    @Override public void showLoading(String msg) {
        super.showLoading();
        if (mProgDialog == null)
            mProgDialog = new ProgressDialog(this);
        mProgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgDialog.setIndeterminate(false);
        mProgDialog.setCancelable(false);
        mProgDialog.setMessage(msg);
        mProgDialog.show();
    }

    @Override public void hideLoading() {
        super.hideLoading();
        if (mProgDialog != null) {
            mProgDialog.hide();
//            mProgDialog.dismiss();
        }
    }

    protected abstract int getMenuResId();

    protected abstract String getToolbarTitle();

    protected abstract Toolbar getToolbarLayout();
}
