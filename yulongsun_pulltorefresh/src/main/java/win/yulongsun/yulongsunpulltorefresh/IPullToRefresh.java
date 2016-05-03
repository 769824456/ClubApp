package win.yulongsun.yulongsunpulltorefresh;

import android.view.View;


/**
 * the interface of pull to refresh
 */
public interface IPullToRefresh<T extends View> {
    /**
     * set whether pull down is enabled
     * @param pullRefreshEnabled true means enabled，false means disabled
     */
    void setPullRefreshEnabled(boolean pullRefreshEnabled);

    /**
     * set whether pull up is enabled
     * @param pullLoadEnabled true means enabled，false means disabled
     */
    void setPullLoadEnabled(boolean pullLoadEnabled);

    /**
     * set whether auto load when scroll to the bottom
     * @param scrollLoadEnabled true means the function of pull-up will be disabled
     */
    void setScrollLoadEnabled(boolean scrollLoadEnabled);

    boolean isPullRefreshEnabled();

    boolean isPullLoadEnabled();

    /**
     * whether auto load when scroll to the bottom
     * @return true - yes，false - no
     */
    boolean isScrollLoadEnabled();

    void setOnRefreshListener(OnRefreshListener<T> refreshListener);

    void onPullDownRefreshComplete();

    void onPullUpRefreshComplete();

    T getRefreshableView();

    LoadingLayout getHeaderLoadingLayout();

    LoadingLayout getFooterLoadingLayout();

    /**
     * set last update time label
     */
    void setLastUpdatedLabel(CharSequence label);
}
