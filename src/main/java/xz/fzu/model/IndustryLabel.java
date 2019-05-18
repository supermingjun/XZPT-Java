package xz.fzu.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Auto-generator
 *
 * @author Murphy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "industry_label")
@Data
public class IndustryLabel implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    private long id;
    private String content;

}
