package win.yulongsun.yulongsunpulltorefresh;

/**
 * pull-up and pull-down UI interface
 */
public interface ILoadingLayout {


    void setState(State state);

    State getState();

    /**
     * get current height of the layout，the critical value of refresh
     * @return height
     */
    int getContentSize();

    /**
     * call the method when pull
     * @param scale : the scale of pulling
     */
    void onPull(float scale);
}
