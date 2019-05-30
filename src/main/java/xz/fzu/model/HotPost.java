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
    private Long id;
    private Long recruitmentId;
    private Integer heat;

    public void heatAdd(int hotLevel) {
        heat = heat + hotLevel;
    }

    @Override
    public int compareTo(HotPost o) {
        return this.getHeat() > o.getHeat() ? 0 : -1;
    }
}
