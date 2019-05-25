package xz.fzu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Murphy
 * @date 2019/5/25 12:33
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ChatDTO {

    public static final int COMPANY = 0;
    public static final int USER = 1;
    private String userId;
    private String userName;
    private String headUrl;
}
