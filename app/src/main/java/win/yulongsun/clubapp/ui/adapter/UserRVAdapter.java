package win.yulongsun.clubapp.ui.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.List;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.net.entity.UserVo;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.adapter
 * USER : yulongsun on 2016/5/4
 * NOTE :
 */
public class UserRVAdapter extends SuperAdapter<UserVo> {
    public UserRVAdapter(Context context, List<UserVo> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, final UserVo item) {
        holder.setImageUrl(getContext(), R.id.iv_item_user_avatar, item.avatar);
        holder.setText(R.id.tv_item_user_name, item.name);
        holder.setText(R.id.tv_item_user_job_id, item.job_id + "");
        holder.setText(R.id.tv_item_user_mobile, item.mobile + "");
        holder.setOnClickListener(R.id.btn_item_user_call, new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.mobile));
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                getContext().startActivity(intent);
            }
        });
    }
//    private List<UserVo>   mList;
//    private LayoutInflater mLayoutInflater;
//    private Context        mContext;
//
//    public UserRVAdapter(Context mContext, List<UserVo> mList) {
//        super();
//        this.mContext = mContext;
//        this.mList = mList;
//        mLayoutInflater = LayoutInflater.from(mContext);
//    }
//
//    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View       mView       = mLayoutInflater.inflate(R.layout.item_rv_user, null);
//        ViewHolder mViewHolder = new ViewHolder(mView);
//        return mViewHolder;
//    }
//
//    @Override public void onBindViewHolder(ViewHolder holder, final int position) {
//        UserVo userVo = mList.get(position);
//        holder.mTvItemUserJobIdAndName.setText(userVo.job_id + "--" + userVo.name);
//        holder.mTvItemUserPhone.setText(userVo.mobile);
//        holder.mBtnItemUserCall.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                ToastUtils.showMessage(mContext, String.valueOf(position));
//            }
//        });
//        holder.llItemUser.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                Intent intent = new Intent(mContext, UserUpdateActivity.class);
//                mContext.startActivity(intent);
//            }
//        });
//        Glide.with(mContext).load(userVo.avatar).into(holder.mIvItemUserAvatar);
//    }
//
//    @Override public int getItemCount() {
//        return mList == null ? 0 : mList.size();
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.ll_item_user)                 LinearLayout llItemUser;
//        @Bind(R.id.iv_item_user_avatar)          ImageView    mIvItemUserAvatar;
//        @Bind(R.id.tv_item_user_job_id_and_name) TextView     mTvItemUserJobIdAndName;
//        @Bind(R.id.tv_item_user_phone)           TextView     mTvItemUserPhone;
//        @Bind(R.id.btn_item_user_call)           Button       mBtnItemUserCall;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            itemView.setTag(this);
//        }
//    }
//
//    public void addData(int position, List<UserVo> data) {
//        if (mList != null && data.size() > 0) {
//            mList.addAll(data);//所有元素添加到此列表的尾部
//            notifyItemRangeChanged(position, data.size());
//        }
//    }
//
//    public UserVo getData(int position) {
//        return mList.get(position);
//    }
//
//    public List<UserVo> getDatas() {
//        return mList;
//    }
//
//    public void clearData() {
//        mList.clear();
//        notifyItemRangeRemoved(0, mList.size());
//    }
//
//    public void addData(List<UserVo> datas) {
//        addData(0, datas);
//    }

}
