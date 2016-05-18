package win.yulongsun.clubapp.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.List;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.net.entity.MemberVo;
import win.yulongsun.yulongsunutils.utils.DateUtil;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.adapter
 * USER : yulongsun on 2016/4/27
 * NOTE :
 */
public class MemberRVAdapter extends SuperAdapter<MemberVo> {
    private Context mContext;

    public MemberRVAdapter(Context context, List<MemberVo> items, int layoutResId) {
        super(context, items, layoutResId);
        this.mContext = context;
    }

    @Override public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MemberVo item) {
        holder.setText(R.id.tv_item_member_name, item.name);
        holder.setText(R.id.tv_item_member_mobile, item.mobile);
        holder.setImageUrl(mContext, R.id.iv_item_member_avatar, item.avatar);
        holder.setText(R.id.tv_item_member_card_id, item.card_id + "");
        if (item.gender == 0) {
            holder.setImageBitmap(R.id.iv_item_member_gender, BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ic_gender_girl));
        } else {
            holder.setImageBitmap(R.id.iv_item_member_gender, BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ic_gender_boy));
        }
        holder.setText(R.id.tv_item_member_create_time, item.create_time + "");

    }
//    private List<MemberVo> mList;
//    private LayoutInflater mLayoutInflater;
//    private Context        mContext;
//
//    public MemberRVAdapter(Context mContext, List<MemberVo> mList) {
//        super();
//        this.mContext = mContext;
//        this.mList = mList;
//        mLayoutInflater = LayoutInflater.from(mContext);
//    }
//
//    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View       mView       = mLayoutInflater.inflate(, null);
//        ViewHolder mViewHolder = new ViewHolder(mView);
//        return mViewHolder;
//    }
//
//    @Override public void onBindViewHolder(ViewHolder holder, int position) {
//        MemberVo mMemberVo = mList.get(position);
////        Glide.with(mContext).
//        holder.tv_item_member_name.setText(mMemberVo.name);
//        holder.tv_item_member_mobile.setText(mMemberVo.mobile);
//    }
//
//    @Override public int getItemCount() {
//        return mList == null ? 0 : mList.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView iv_item_member_avatar;
//        TextView  tv_item_member_name;
//        TextView  tv_item_member_mobile;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            iv_item_member_avatar = (ImageView) itemView.findViewById(R.id.iv_item_member_avatar);
//            tv_item_member_name = (TextView) itemView.findViewById(R.id.tv_item_member_name);
//            tv_item_member_mobile = (TextView) itemView.findViewById(R.id.tv_item_member_mobile);
//            itemView.setTag(this);
//        }
//    }
//
//    public void addList(List<MemberVo> data) {
//        if (mList != null) {
//            mList.addAll(data);
//            notifyDataSetChanged();
//        }
//    }

}
