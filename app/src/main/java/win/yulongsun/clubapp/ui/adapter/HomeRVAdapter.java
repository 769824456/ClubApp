package win.yulongsun.clubapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import win.yulongsun.clubapp.R;
import win.yulongsun.clubapp.net.entity.HomeRV;
import win.yulongsun.clubapp.ui.activity.member.MemberConsumeActivity;
import win.yulongsun.clubapp.ui.activity.member.MemberHistoryActivity;
import win.yulongsun.clubapp.ui.activity.member.MemberRechargeActivity;
import win.yulongsun.clubapp.ui.activity.member.MemberSearchActivity;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.adapter
 * USER : yulongsun on 2016/5/4
 * NOTE :
 */
public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.ViewHolder> {


    private List<HomeRV>   mList;
    private LayoutInflater mLayoutInflater;
    private Context        mContext;


    public HomeRVAdapter(Context mContext, List<HomeRV> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View       mView       = mLayoutInflater.inflate(R.layout.item_rv_home, null);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override public void onBindViewHolder(ViewHolder holder, final int position) {
        HomeRV homeRV = mList.get(position);
        holder.mRlItemHome.setBackgroundColor(mContext.getResources().getColor(homeRV.rl_item_home));
        holder.mIvItemHome.setImageResource(homeRV.iv_item_home);
        holder.mTvItemHome.setText(homeRV.tv_item_home);

        //onClick
        holder.mRlItemHome.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = null;
                switch (position) {
                    case 0://搜索
                        intent = new Intent(mContext, MemberSearchActivity.class);
                        break;
                    case 1://消费
                        intent = new Intent(mContext, MemberConsumeActivity.class);
                        break;
                    case 2://充值
                        intent = new Intent(mContext, MemberRechargeActivity.class);
                        break;
                    case 3://历史
                        intent = new Intent(mContext, MemberHistoryActivity.class);
                        break;
                }
                if (intent != null) {
                    mContext.startActivity(intent);
                }

            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_item_home) ImageView      mIvItemHome;
        @Bind(R.id.tv_item_home) TextView       mTvItemHome;
        @Bind(R.id.rl_item_home) RelativeLayout mRlItemHome;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setTag(this);
        }

    }
}
