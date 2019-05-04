package xz.fzu.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Auto-generator
 *
 * @author Murphy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndustryLabel implements Serializable {

    private static final long serialVersionUID = 1;
    private long id;
    private String content;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
