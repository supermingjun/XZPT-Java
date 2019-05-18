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
@Table(name = "company")
@Data
public class Company implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private String companyId;
    private String telephone;
    private String passwd;
    private String companyName;
    private String headUrl;
    private String email;
    private String description;
    private long status;
    private String token;

}
