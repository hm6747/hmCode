package cn.hmst.dto;

import cn.hmst.pojo.SysDept;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hm on 2017/12/23.
 */
@Getter
@Setter
@ToString
public class SysDeptDto extends  SysDept implements Serializable{
    private List<SysDeptDto> deptDtoList = Lists.newArrayList();

    public static SysDeptDto adapt(SysDept dept) {
        SysDeptDto dto = new SysDeptDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
