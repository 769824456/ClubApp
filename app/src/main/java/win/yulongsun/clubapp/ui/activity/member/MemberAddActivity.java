package win.yulongsun.clubapp.ui.activity.member;

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
import android.widget.Spinner;

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
import win.yulongsun.yulongsunutils.ValidateUtils;
import win.yulongsun.yulongsunutils.cache.ACache;
import win.yulongsun.yulongsunutils.common.BaseToolbarActivity;
import win.yulongsun.yulongsunutils.utils.GsonUtils;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

//添加会员
public class MemberAddActivity extends BaseToolbarActivity {

    private static final String TAG = MemberAddActivity.class.getSimpleName();
    @Bind(R.id.tl_member_add)         Toolbar           mTlMemberAdd;
    @Bind(R.id.civ_member_add_avatar) CircleImageView   mCivMemberAddAvatar;
    @Bind(R.id.et_member_add_name)    EditText          mEtMemberAddName;
    @Bind(R.id.et_member_add_mobile)  EditText          mEtMemberAddMobile;
    @Bind(R.id.et_member_add_score)   EditText          mEtMemberAddScore;
    @Bind(R.id.sp_member_add_rank)    Spinner           mSpMemberAddRank;
    @Bind(R.id.et_member_add_card_id) EditText          mEtMemberAddCardId;
    @Bind(R.id.rb_member_add_boy)     RadioButton       mRbMemberAddBoy;
    @Bind(R.id.rb_member_add_girl)    RadioButton       mRbMemberAddGirl;
    @Bind(R.id.rg_member_add_gender)  RadioGroup        mRgMemberAddGender;
    @Bind(R.id.et_member_add_addr)    EditText          mEtMemberAddAddr;
    private                           ImageFileSelector mImageFileSelector;
    private                           String            member_avatar;
    private int member_gender = 0;

    @Override public int getLayoutResId() {
        return R.layout.activity_member_add;
    }


    @Override protected int getMenuResId() {
        return R.menu.menu_done;
    }

    @Override protected String getToolbarTitle() {
        return "添加会员";
    }

    @Override protected Toolbar getToolbarLayout() {
        return mTlMemberAdd;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            addMember();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*添加会员*/
    private void addMember() {
        String member_name    = mEtMemberAddName.getText().toString();
        String member_mobile  = mEtMemberAddMobile.getText().toString();
        String member_score   = mEtMemberAddScore.getText().toString();
        int    member_rank    = mSpMemberAddRank.getSelectedItemPosition();
        String member_card_id = mEtMemberAddCardId.getText().toString();
        String member_addr    = mEtMemberAddAddr.getText().toString();
        if (ValidateUtils.isTextNull(member_name)) {
            ToastUtils.showMessage(MemberAddActivity.this, "会员名不能为空");
            return;
        }
        if (!ValidateUtils.isMobilePattern(member_mobile)) {
            ToastUtils.showMessage(MemberAddActivity.this, ToastUtils.ERROR_PHONE);
            return;
        }
        if (ValidateUtils.isTextNull(member_score)) {
            ToastUtils.showMessage(MemberAddActivity.this, "积分不能为空");
            return;
        }
        if (ValidateUtils.isTextNull(member_card_id)) {
            ToastUtils.showMessage(MemberAddActivity.this, "消费卡编号不能为空");
            return;
        }

        String member_operator_id = ACache.get(MemberAddActivity.this).getAsString("user_id");
        String member_c_id        = ACache.get(MemberAddActivity.this).getAsString("user_c_id");
        showLoading("添加中....");
        OkHttpUtils.post().url(Api.HOST + Api.MEMBER + "addMember")
                .addFile("member_avatar", "member_avatar", new File(member_avatar))
                .addParams("member_name", member_name)
                .addParams("member_mobile", member_mobile)
                .addParams("member_score", member_score)
                .addParams("member_rank", member_rank + "")
                .addParams("member_addr", member_addr)
                .addParams("member_card_id", member_card_id)
                .addParams("member_c_id", member_c_id)
                .addParams("member_operator_id", member_operator_id)
                .addParams("member_gender", member_gender + "")
                .build()
                .execute(new StringCallback() {
                    @Override public void onError(Call call, Exception e) {
                        hideLoading();
                        e.printStackTrace();
                    }

                    @Override public void onResponse(String response) {
                        hideLoading();
                        Log.d(TAG, "onResponse: " + response);
                        NullResponse baseResponse = GsonUtils.changeGsonToBean(response, NullResponse.class);
                        if (baseResponse.errorCode == 0) {
                            ToastUtils.showMessage(MemberAddActivity.this, "添加会员成功");
                            MemberAddActivity.this.finish();
                        } else {
                            ToastUtils.showMessage(MemberAddActivity.this, baseResponse.errorMsg);
                        }
                    }
                });


    }

    @Override protected void initViews() {
        super.initViews();
        //gender
        mRgMemberAddGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRbMemberAddBoy.getId()) {
                    Log.d(TAG, "onCheckedChanged: boy");
                    member_gender = 1;
                } else {
                    Log.d(TAG, "onCheckedChanged: girl");
                    member_gender = 0;
                }
            }
        });
        //img
        mImageFileSelector = new ImageFileSelector(this);
        mImageFileSelector.setCallback(new ImageFileSelector.Callback() {
            @Override
            public void onSuccess(final String file) { // 选取图片成功
                member_avatar = file;
                Bitmap bitmap = BitmapFactory.decodeFile(file);
                mCivMemberAddAvatar.setImageBitmap(bitmap);
            }

            @Override
            public void onError() { // 选取图片失败
                ToastUtils.showMessage(MemberAddActivity.this, "选取图片失败");
            }
        });
    }

    @OnClick(R.id.civ_member_add_avatar) void btnChooseImage() {
        new AlertDialog.Builder(this).setTitle("选择图片").setItems(new String[]{"相册", "拍照"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Constants.TAKE_PHOTO_REQUEST://拍照
                        // 拍照选取
                        if (ContextCompat.checkSelfPermission(MemberAddActivity.this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MemberAddActivity.this, new String[]{Manifest.permission.CAMERA},
                                    9);
                        } else {
                            mImageFileSelector.takePhoto(MemberAddActivity.this);
                        }
                        break;
                    case Constants.PHOTO_ALBUM_REQUEST://相册
                        // 从文件选取
                        mImageFileSelector.selectImage(MemberAddActivity.this);
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
