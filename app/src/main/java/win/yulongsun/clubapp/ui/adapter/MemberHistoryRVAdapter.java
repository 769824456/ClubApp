package win.yulongsun.clubapp.ui.adapter;

import android.content.Context;

import org.byteam.superadapter.IMulItemViewType;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.internal.SuperViewHolder;

import java.util.List;

import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.net.entity.MemberVo;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.adapter
 * USER : yulongsun on 2016/5/17
 * NOTE :
 */
public class MemberHistoryRVAdapter extends SuperAdapter<MemberVo> {
    public MemberHistoryRVAdapter(Context context, List<MemberVo> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MemberVo item) {
        holder.setText(R.id.tv_item_member_history_num, item.num + "元");
        holder.setText(R.id.tv_item_member_history_name, item.name);
        holder.setText(R.id.tv_item_member_history_card_id, item.card_id + "");
        holder.setText(R.id.tv_item_member_history_create_time, item.create_time);
    }
}
