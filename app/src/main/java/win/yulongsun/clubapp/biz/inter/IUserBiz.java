package win.yulongsun.clubapp.biz.inter;

/*
 * PACKAGE_NAME :cn.ace.android_mvp.biz.inter
 * VERSION :[V 1.0.0]
 * AUTHOR :  yulongsun
 * CREATE AT : 8/1/2015 3:51 PM
 * COPYRIGHT : InSigma HengTian Software Ltd.
 * E-MAIL: yulongsun@hengtiansoft.com
 * NOTE : 业务层接口
 */
public interface IUserBiz {

    /**登录操作
     * @param username 用户名
     * @param password 密码
     * @param loginListener 登录结果
     */
     void login(String username, String password, OnLoginListener loginListener);
}
