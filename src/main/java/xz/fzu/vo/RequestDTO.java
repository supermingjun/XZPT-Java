package xz.fzu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Murphy
 * @date 2019/5/18 18:46
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RequestDTO<T> implements Serializable {

    private static final long serialVersionUID = 1;
    private String token;
    private T requestObject;
}
