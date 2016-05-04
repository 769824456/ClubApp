package win.yulongsun.clubapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.entity.MemberVo;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.adapter
 * USER : yulongsun on 2016/4/27
 * NOTE :
 */
public class MemberRVAdapter extends RecyclerView.Adapter<MemberRVAdapter.ViewHolder> {
    private List<MemberVo> mList;
    private LayoutInflater mLayoutInflater;
    private Context        mContext;

    public MemberRVAdapter(Context mContext, List<MemberVo> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View       mView       = mLayoutInflater.inflate(R.layout.item_rv_member, null);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        MemberVo mMemberVo = mList.get(position);
//        Glide.with(mContext).
        holder.tv_item_member_name.setText(mMemberVo.name);
        holder.tv_item_member_mobile.setText(mMemberVo.mobile);
    }

    @Override public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_item_member_avatar;
        TextView  tv_item_member_name;
        TextView  tv_item_member_mobile;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_item_member_avatar = (ImageView) itemView.findViewById(R.id.iv_item_member_avatar);
            tv_item_member_name = (TextView) itemView.findViewById(R.id.tv_item_member_name);
            tv_item_member_mobile = (TextView) itemView.findViewById(R.id.tv_item_member_mobile);
            itemView.setTag(this);
        }
    }


}
