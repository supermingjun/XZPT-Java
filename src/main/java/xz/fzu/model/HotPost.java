package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 热门岗位对象
 *
 * @author LITM
 * @time:2019年5月3日 23:13:23
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HotPost implements Comparable<HotPost> {
    private Integer id;
    private Integer recruitmentId;
    private Integer heat;

    public void heatAdd() {
        heat = heat + 1;
    }

    @Override
    public int compareTo(HotPost o) {
        return this.getHeat() > o.getHeat() ? 0 : -1;
    }
}
