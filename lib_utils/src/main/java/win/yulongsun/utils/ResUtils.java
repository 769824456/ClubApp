package win.yulongsun.utils;

import java.lang.reflect.Field;

/**
 * PROJECT_NAME : ClubApp
 * PACKAGE_NAME : win.yulongsun.yulongsunutils.utils
 * USER : yulongsun on 2016/5/7
 * NOTE :
 */
public class ResUtils {
    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
