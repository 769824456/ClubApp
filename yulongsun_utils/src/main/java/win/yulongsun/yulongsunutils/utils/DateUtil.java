package win.yulongsun.yulongsunutils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sf = null;


    /*获取刷新时间 格式为：“HH:mm:ss”*/
    public static String getRefreshTime() {
        Date d = new Date();
        sf = new SimpleDateFormat("HH:mm:ss");
        return sf.format(d);
    }

    /*获取刷新时间 格式为：“YYYY-MM-DD HH:mm:ss”*/
    public static String getChkTime() {
        Date d = new Date();
        sf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        return sf.format(d);
    }


    /* 获取系统时间 格式为："yyyy/MM/dd " */
    public static String getCurrentTime() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy/MM/dd");
        return sf.format(d);
    }

    /* 时间戳转换成字符窜 */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /* 将字符串转为时间戳 */
    public static Date getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}