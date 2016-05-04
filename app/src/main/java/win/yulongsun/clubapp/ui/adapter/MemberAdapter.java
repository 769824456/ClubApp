package win.yulongsun.clubapp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import win.yulongsun.clubapp.R;
import win.yulongsun.yulongsunutils.common.CommonAdapter;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.clubapp.ui.adapter
 * USER : yulongsun on 2016/4/13
 * NOTE :
 */
public class MemberAdapter extends CommonAdapter {


    public MemberAdapter(Context mContext) {
        super(mContext);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder;
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.item_rv_member, null);
            holder = new HolderView();
//            holder.tvUser = (TextView) convertView.findViewById(R.id.tvUser);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
//        WUser item = (WUser) getItem(position);
//        holder.tvUser.setText(item.getCustomerName());
        return convertView;
    }

    class HolderView {
        TextView tvUser;
    }
}
