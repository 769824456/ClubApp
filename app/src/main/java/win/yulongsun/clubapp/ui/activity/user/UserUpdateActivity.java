package win.yulongsun.clubapp.ui.activity.user;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sw926.imagefileselector.ImageFileSelector;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import win.yulongsun.clubapp.R;
import win.yulongsun.uiframework.BaseToolbarActivity;
import win.yulongsun.utils.ToastUtils;

//修改店员信息
public class UserUpdateActivity extends BaseToolbarActivity {

    private static final int TAKE_PHOTO_REQUEST  = 1;
    private static final int PHOTO_ALBUM_REQUEST = 0;
    @Bind(R.id.tl_user_update)        Toolbar           mTlUserUpdate;
    @Bind(R.id.civ_user_update)       CircleImageView   mCivUserUpdate;
    @Bind(R.id.et_user_update_phone)  EditText          mEtUserUpdatePhone;
    @Bind(R.id.et_user_update_pwd)    EditText          mEtUserUpdatePwd;
    @Bind(R.id.et_user_update_job_id) EditText          mEtUserUpdateJobId;
    @Bind(R.id.et_user_update_name)   EditText          mEtUserUpdateName;
    @Bind(R.id.rb_user_update_boy)    RadioButton       mRbUserUpdateBoy;
    @Bind(R.id.rb_user_update_girl)   RadioButton       mRbUserUpdateGirl;
    @Bind(R.id.rg_user_update_gender) RadioGroup        mRgUserUpdateGender;
    @Bind(R.id.et_user_update_addr)   EditText          mEtUserUpdateAddr;
    private                           ImageFileSelector mImageFileSelector;
    private String TAG = UserUpdateActivity.class.getSimpleName();

    @Override public int getLayoutResId() {
        return R.layout.activity_user_update;
    }


    @Override protected String getToolbarTitle() {
        return "修改会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlUserUpdate;
    }

    @Override protected int getMenuResId() {
        return R.menu.menu_done;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void initListeners() {
        super.initListeners();
        //gender
        mRgUserUpdateGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRgUserUpdateGender.getId()) {
                    Log.d(TAG, "onCheckedChanged: boy");
                } else {
                    Log.d(TAG, "onCheckedChanged: girl");
                }
            }
        });

        //img
        mImageFileSelector = new ImageFileSelector(this);
        mImageFileSelector.setCallback(new ImageFileSelector.Callback() {
            @Override
            public void onSuccess(final String file) { // 选取图片成功
                Bitmap bitmap = BitmapFactory.decodeFile(file);
                mCivUserUpdate.setImageBitmap(bitmap);
            }

            @Override
            public void onError() { // 选取图片失败
                ToastUtils.showMessage(UserUpdateActivity.this, "选取图片失败");
            }
        });
    }


    @OnClick(R.id.civ_user_update) void btnChooseImage() {
        new AlertDialog.Builder(this).setTitle("选择图片").setItems(new String[]{"相册", "拍照"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PHOTO_REQUEST://拍照
                        // 拍照选取
                        if (ContextCompat.checkSelfPermission(UserUpdateActivity.this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(UserUpdateActivity.this, new String[]{Manifest.permission.CAMERA},
                                    9);
                        } else {
                            mImageFileSelector.takePhoto(UserUpdateActivity.this);
                        }
                        break;
                    case PHOTO_ALBUM_REQUEST://相册
                        // 从文件选取
                        mImageFileSelector.selectImage(UserUpdateActivity.this);
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
