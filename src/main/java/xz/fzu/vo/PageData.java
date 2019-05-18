package xz.fzu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/29 19:13
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1;

    /**
     * 当前请求的页数（1-n）
     */
    private int currentPage;
    /**
     * 总共有多少页
     */
    private int numOfPage;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 内容数组
     */
    private List<T> contentList;
}
