package win.yulongsun.utils.utils;


import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import win.yulongsun.component.response.ResponseList;


/**
 *
 */
public class GsonUtils {

    public GsonUtils() {
    }

    public static String createGsonString(Object object) {
        Gson   gson       = new Gson();
        String gsonString = gson.toJson(object);
        return gsonString;
    }

    public static <T> T parseToBean(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        T    t    = gson.fromJson(gsonString, cls);
        return t;
    }

    public static <T> ResponseList<T> changeGsonToList(String gsonString, Class<T> cls) {
        Gson            gson         = new Gson();
        ResponseList<T> responseList = gson.fromJson(gsonString, new TypeToken<ResponseList<T>>() {
        }.getType());
        return responseList;
    }


    public static <T> List<Map<String, T>> changeGsonToListMaps(
            String gsonString) {
        List<Map<String, T>> list = null;
        Gson                 gson = new Gson();
        list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
        Map<String, T> map  = null;
        Gson           gson = new Gson();
        map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }

}
