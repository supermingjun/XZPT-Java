package xz.fzu.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Auto-generator
 *
 * @author Murphy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationLabel implements Serializable {

    private static final long serialVersionUID = 1;
    private long stationId;
    private String content;


    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
