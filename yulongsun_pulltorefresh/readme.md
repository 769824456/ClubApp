1. 类名
（1）IPullToRefresh      上拉下拉刷新接口文件
（2）ILoadingLayout      下拉刷新和上拉加载更多的界面接口
（3）FooterLoadingLayout     封装了上拉加载的布局
（4）HeaderLoadingLayout.       封装了下拉刷新的布局
（5）LoadingLayout     定义了Header和Footer的共同行为
（6）PullToRefreshBase     下拉刷新和上拉加载更多的功能的实现文档
（7）PullToRefreshListView     封装了ListView下拉刷新，上加载更多和滑到底部自动加载
（8）PullToRefreshScrollView     封装了ScrollView的下拉刷新
（9）RotateLoadingLayout     封装了下拉刷新的布局
2. 公共属性及方法
（1）公共属性
/** 当前的状态*/
public enum State {
/* Initial state*/
NONE,
/**
* When the UI is in a state which means that user is not interacting
* with the Pull-to-Refresh function.
*/
RESET,
/**
* When the UI is being pulled by the user, but has not been pulled far
* enough so that it refreshes when released.
*/
PULL_TO_REFRESH,
/**
* When the UI is being pulled by the user, and <strong>has</strong>
* been pulled far enough so that it will refresh when released.
*/
RELEASE_TO_REFRESH,
/*When the UI is currently refreshing, caused by a pull gesture.*/
REFRESHING,
/* No more data*/
NO_MORE_DATA,
/* No more data*/
NO_DATA
}

（2） 公共方法
<1> 上拉加载/下拉刷新接口
    /**
     * 设置当前下拉刷新是否可用
     * @param pullRefreshEnabled true表示可用，false表示不可用
     */
    public void setPullRefreshEnabled(boolean pullRefreshEnabled);

    /**
     * 设置当前上拉加载更多是否可用
     * @param pullLoadEnabled true表示可用，false表示不可用
     */
    public void setPullLoadEnabled(boolean pullLoadEnabled);

    /**
     * 滑动到底部是否自动加载更多数据
     * @param scrollLoadEnabled 如果这个值为true的话，那么上拉加载更多的功能将会禁用
     */
    public void setScrollLoadEnabled(boolean scrollLoadEnabled);

    /**
     * 判断当前下拉刷新是否可用
     * @return true如果可用，false不可用
     */
    public boolean isPullRefreshEnabled();

    /**
     * 判断上拉加载是否可用
     * @return true可用，false不可用
     */
    public boolean isPullLoadEnabled();

    /**
     * 滑动到底部加载是否可用
     * @return true可用，否则不可用
     */
    public boolean isScrollLoadEnabled();

    /**
     * 设置刷新的监听器
     * @param refreshListener 监听器对象
     */
    public void setOnRefreshListener(OnRefreshListener<T> refreshListener);

    /**
     * 结束下拉刷新
     */
    public void onPullDownRefreshComplete();

    /**
     * 结束上拉加载更多
     */
    public void onPullUpRefreshComplete();

    /**
     * 得到可刷新的View对象
     * @return 返回调用{@link #createRefreshableView(Context, AttributeSet)} 方法返回的对象
     */
    public T getRefreshableView();

    /**
     * 得到Header布局对象
     * @return Header布局对象
     */
    public LoadingLayout getHeaderLoadingLayout();

    /**
     * 得到Footer布局对象
     * @return Footer布局对象
     */
    public LoadingLayout getFooterLoadingLayout();

    /**
     * 设置最后更新的时间文本
     * @param label 文本
     */
public void setLastUpdatedLabel(CharSequence label);

<2>  上拉/下拉事件监听接口
/**
* 下拉
*/
void onPullDownToRefresh(final PullToRefreshBase<V> refreshView);

/**
* 上拉
*/
void onPullUpToRefresh(final PullToRefreshBase<V> refreshView);

<3> 上拉/下拉界面接口  
    /**
     * 设置当前状态，派生类应该根据这个状态的变化来改变View的变化
     * @param state 状态
     */
    public void setState(State state);

    /**
     * 得到当前的状态
     * @return 状态
     */
    public State getState();

    /**
     * 得到当前Layout的内容大小，它将作为一个刷新的临界点
     * @return 高度
     */
    public int getContentSize();

    /**
     * 在拉动时调用
     * @param scale 拉动的比例
     */
public void onPull(float scale);

3. 使用方法
（1）布局文件中这样引用
    <win.yulongsun.yulongsunpulltorefresh.PullToRefreshListView
        android:id="@+id/pull_refresh_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical" >
    </com.hengtiansoft.pulltorefresh.PullToRefreshListView>
  
（2）获取PullToRefreshListView控件，ListView变量初始化，并得到可刷新的view。
（3）创建PullToRefreshListView控件的上拉/下拉监听器，并实现onPullDownToRefresh(…)和onPullDownToRefresh(…)方法。

代码片段：

		mPullListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_view);
		mPullListView.setPullLoadEnabled(false);
		mPullListView.setScrollLoadEnabled(true);
		mListView = mPullListView.getRefreshableView();
		mListView.setDivider(null);
		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listItem);
		mListView.setAdapter(mAdapter);
		listItem.addAll(Arrays.asList(mStrings).subList(0, mCurIndex));
		mPullListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				isPullDownRefreshing = true;
				refreshData();
				setLastUpdateTime();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isPullUpRefreshing = true;
				int size = listItem.size();
				refreshData();
			}
		});
		setLastUpdateTime();
	}