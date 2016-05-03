package win.yulongsun.yulongsunpulltorefresh;

/**
 * current state
 */
enum State {
    /**
     * Initial state
     */
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
    /**
     * When the UI is currently refreshing, caused by a pull gesture.
     */
    REFRESHING,
    /**
     * No more data
     */
    NO_MORE_DATA,
    /**
     * No more data
     */
    NO_DATA
}