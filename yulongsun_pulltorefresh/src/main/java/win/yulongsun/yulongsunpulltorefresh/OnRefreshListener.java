package win.yulongsun.yulongsunpulltorefresh;

import android.view.View;

interface OnRefreshListener<V extends View> {
    void onPullDownToRefresh(final PullToRefreshBase<V> refreshView);

    void onPullUpToRefresh(final PullToRefreshBase<V> refreshView);
}
