package win.yulongsun.utils;

import android.text.TextUtils;

/**
 * @Project TaoQuanUserClient
 * @Packate win.yulongsun.util
 * @Author yulongsun
 * @Email yulongsun@gmail.com
 * @Date 2016/3/8
 * @Version 1.0.0
 * @Description
 */
public class ValidateUtils {

    /*文本是否为空*/
    public static boolean isTextNull(String text) {
        if (text == null || text.length() == 0 || "".equals(text)) {
            return true;
        }
        return false;
    }


    /*判断邮箱地址是否有效*/
    public static boolean isEmailValid(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }


    /*校验密码长度*/
    public static boolean isPwdValid(String pwd) {
        if (pwd.length() < 6 || pwd.length() > 16) {
            return false;
        }
        return true;
    }

    /*校验用户名长度*/
    public static boolean isNameValid(String name) {
        if (name.length() < 2 || name.length() > 16) {
            return false;
        }
        return true;
    }


    /*验证手机格式*/
    public static boolean isMobilePattern(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186
		 * 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

}
