package win.yulongsun.clubapp.ui.activity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sw926.imagefileselector.ImageFileSelector;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.common.Api;
import win.yulongsun.clubapp.common.Constants;
import win.yulongsun.clubapp.net.response.NullResponse;
import win.yulongsun.component.cache.ACache;
import win.yulongsun.uiframework.BaseToolbarActivity;
import win.yulongsun.utils.GsonUtils;
import win.yulongsun.utils.MD5Utils;
import win.yulongsun.utils.ToastUtils;
import win.yulongsun.utils.ValidateUtils;

//添加店员
public class UserAddActivity extends BaseToolbarActivity {

    private static final int TAKE_PHOTO_REQUEST  = 1;
    private static final int PHOTO_ALBUM_REQUEST = 0;
    @Bind(R.id.tl_user_add)        Toolbar           mTlUserAdd;
    @Bind(R.id.civ_user_add)       CircleImageView   mCivUserAdd;
    @Bind(R.id.et_user_add_mobile) EditText          mEtUserAddMobile;
    @Bind(R.id.et_user_add_pwd)    EditText          mEtUserAddPwd;
    @Bind(R.id.et_user_add_job_id) EditText          mEtUserAddJobId;
    @Bind(R.id.et_user_add_name)   EditText          mEtUserAddName;
    @Bind(R.id.et_user_add_addr)   EditText          mEtUserAddAddr;
    @Bind(R.id.rb_user_add_boy)    RadioButton       mRbUserAddBoy;
    @Bind(R.id.rb_user_add_girl)   RadioButton       mRbUserAddGirl;
    @Bind(R.id.rg_user_add_gender) RadioGroup        mRgUserAddGender;
    private                        ImageFileSelector mImageFileSelector;
    private                        String            user_avatar;
    private String TAG         = UserAddActivity.class.getSimpleName();
    private int    user_gender = 1;

    @Override public int getLayoutResId() {
        return R.layout.activity_user_add;
    }


    @Override protected String getToolbarTitle() {
        return "添加会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlUserAdd;
    }

    @Override protected int getMenuResId() {
        return R.menu.menu_done;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            addUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addUser() {
        String user_mobile = mEtUserAddMobile.getText().toString();
        String user_pwd    = mEtUserAddPwd.getText().toString();
        String user_job_id = mEtUserAddJobId.getText().toString();
        String user_addr   = mEtUserAddAddr.getText().toString();
        String user_name   = mEtUserAddName.getText().toString();
        String user_c_id   = ACache.get(this).getAsString(Constants.USER_C_ID);
        if (!ValidateUtils.isMobilePattern(user_mobile)) {
            ToastUtils.showMessage(UserAddActivity.this, ToastUtils.ERROR_MOBILE);
            return;
        }
        if (!ValidateUtils.isPwdValid(user_pwd)) {
            ToastUtils.showMessage(UserAddActivity.this, ToastUtils.ERROR_PWD);
            return;
        }
        if (ValidateUtils.isTextNull(user_job_id)) {
            ToastUtils.showMessage(UserAddActivity.this, "工号不能为空");
            return;
        }
        if (ValidateUtils.isTextNull(user_avatar)) {
            ToastUtils.showMessage(UserAddActivity.this, "头像不能为空");
            return;
        }
        if (ValidateUtils.isTextNull(user_name)) {
            ToastUtils.showMessage(UserAddActivity.this, "用户名不能为空");
            return;
        }

        showLoading("添加中....");
        OkHttpUtils.post().url(Api.HOST + Api.USER + "addUser")
                .addFile("user_avatar", user_mobile + ".jpg",new File(user_avatar))
                .addParams("user_mobile", user_mobile)
                .addParams("user_pwd", MD5Utils.getMD5Str(user_pwd))
                .addParams("user_gender", user_gender+"")
                .addParams("user_addr", user_addr)
                .addParams("user_job_id", user_job_id)
                .addParams("user_c_id", user_c_id)
                .addParams("user_name", user_name)
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }

                    @Override public void onResponse(String response) {
                        hideLoading();
                        Log.d(TAG, "onResponse: " + response);
                        NullResponse baseResponse = GsonUtils.parseToBean(response, NullResponse.class);
                        if (baseResponse.errorCode == 0) {
                            ToastUtils.showMessage(UserAddActivity.this, "添加成功");
                            UserAddActivity.this.finish();
                        } else {
                            ToastUtils.showMessage(UserAddActivity.this, baseResponse.errorMsg);
                        }
                    }
                });
    }

    @Override protected void initListeners() {
        super.initListeners();
        //gender
        mRgUserAddGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRbUserAddBoy.getId()) {
                    user_gender = 1;
                    Log.d(TAG, "onCheckedChanged: boy");
                } else {
                    user_gender = 0;
                    Log.d(TAG, "onCheckedChanged: girl");
                }
            }
        });

        //img
        mImageFileSelector = new ImageFileSelector(this);
        mImageFileSelector.setCallback(new ImageFileSelector.Callback() {
            @Override
            public void onSuccess(final String file) { // 选取图片成功
                user_avatar = file;
                Bitmap bitmap = BitmapFactory.decodeFile(file);
                mCivUserAdd.setImageBitmap(bitmap);
            }

            @Override
            public void onError() { // 选取图片失败
                ToastUtils.showMessage(UserAddActivity.this, "选取图片失败");
            }
        });
    }


    @OnClick(R.id.civ_user_add) void btnChooseImage() {
        new AlertDialog.Builder(this).setTitle("选择图片").setItems(new String[]{"相册", "拍照"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PHOTO_REQUEST://拍照
                        // 拍照选取
                        mImageFileSelector.takePhoto(UserAddActivity.this);
                        break;
                    case PHOTO_ALBUM_REQUEST://相册
                        // 从文件选取
                        mImageFileSelector.selectImage(UserAddActivity.this);
                        break;
                }
            }
        }).show();
    }


    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mImageFileSelector.onActivityResult(requestCode, resultCode, data);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mImageFileSelector.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mImageFileSelector.onRestoreInstanceState(savedInstanceState);
    }

    // Android 6.0的动态权限
    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mImageFileSelector.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
