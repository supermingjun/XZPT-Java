package xz.fzu.algorithm;

import xz.fzu.model.Recruitment;

import java.util.Iterator;
import java.util.List;

/**
 * 热门岗位推荐时，过滤掉与用户所选的行业标签不一样的招聘信息
 * @author LITM
 */
public class RecommendHotJobs {
    public static List<Recruitment> recommendHotJobs(Long userIndustryLabel, List<Recruitment> recruitments) {

        if(userIndustryLabel.equals((long)0)){
            return recruitments;
        }
        else {
            Iterator<Recruitment> iterator = recruitments.iterator();
            while(iterator.hasNext()){
                Recruitment recruitment = iterator.next();
                if(recruitment.getIndustryLabel()!=null&&!recruitment.getIndustryLabel().equals(userIndustryLabel)){
                    iterator.remove();
                }
            }
            return recruitments;
        }
    }
}
