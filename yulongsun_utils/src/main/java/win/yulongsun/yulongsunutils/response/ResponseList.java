package win.yulongsun.yulongsunutils.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseList<T> extends BaseResponse {

    public List<T> result = new ArrayList<T>();

    @Override public String toString() {
        return "ResponseList{" +
                "result=" + result +
                '}';
    }
}
