package win.yulongsun.component.response;

import java.util.ArrayList;
import java.util.List;

public class ResponsePageResult<T> {
    public int pageNumber = 1;
    public int totalPages;
    public int pageSize;
    public int totalRows;
    public List<T> rows = new ArrayList<T>();

    @Override
    public String toString() {
        return "ResponseMessagePageResult [pageNumber=" + pageNumber
                + ", totalPages=" + totalPages + ", pageSize=" + pageSize
                + ", totalRows=" + totalRows + ", rows=" + rows + "]";
    }

}
