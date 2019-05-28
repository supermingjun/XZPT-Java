package xz.fzu.util;

import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.vo.PageData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/28 20:32
 */
public class PageUtil {

    /**
     * 对已有数据进行分液处理
     *
     * @param data
     * @param pageData
     * @return java.util.List<T>
     * @author Murphy
     * @date 2019/5/28 20:35
     */
    public static <T> List<T> paging(List<T> data, PageData<T> pageData) throws InstanceNotExistException {

        if (data == null || data.size() == 0) {
            throw new InstanceNotExistException();
        }
        int start = (pageData.getCurrentPage() - 1) * pageData.getPageSize();
        int end = pageData.getPageSize() + start - 1;
        List<T> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (i >= start && i <= end) {
                list.add(data.get(i));
            }
        }
        return list;
    }
}
