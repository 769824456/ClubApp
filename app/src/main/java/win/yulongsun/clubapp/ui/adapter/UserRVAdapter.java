package win.yulongsun.clubapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.entity.UserVo;
import win.yulongsun.clubapp.ui.activity.user.UserUpdateActivity;
import win.yulongsun.yulongsunutils.utils.ToastUtils;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.adapter
 * USER : yulongsun on 2016/5/4
 * NOTE :
 */
public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {
    private List<UserVo>   mList;
    private LayoutInflater mLayoutInflater;
    private Context        mContext;

    public UserRVAdapter(Context mContext, List<UserVo> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View       mView       = mLayoutInflater.inflate(R.layout.item_rv_user, null);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override public void onBindViewHolder(ViewHolder holder, final int position) {
        UserVo userVo = mList.get(position);
        holder.mTvItemUserJobIdAndName.setText(userVo.job_id + "--" + userVo.name);
        holder.mTvItemUserPhone.setText(userVo.mobile);
        holder.mBtnItemUserCall.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                ToastUtils.showMessage(mContext, String.valueOf(position));
            }
        });
        holder.llItemUser.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(mContext, UserUpdateActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ll_item_user)                 LinearLayout llItemUser;
        @Bind(R.id.iv_item_user_avatar)          ImageView    mIvItemUserAvatar;
        @Bind(R.id.tv_item_user_job_id_and_name) TextView     mTvItemUserJobIdAndName;
        @Bind(R.id.tv_item_user_phone)           TextView     mTvItemUserPhone;
        @Bind(R.id.btn_item_user_call)           Button       mBtnItemUserCall;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setTag(this);
        }

    }
}
