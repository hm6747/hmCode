package cn.hmst.dto;

import cn.hmst.pojo.TbItemCat;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5 0005.
 */
@Getter
@Setter
@ToString
public class TbItemCatDto extends TbItemCat{
    private List<TbItemCatDto> itemCatDtoList = Lists.newArrayList();
    public static TbItemCatDto adapt(TbItemCat tbItemCat) {
        TbItemCatDto dto = new TbItemCatDto();
        BeanUtils.copyProperties(tbItemCat, dto);
        return dto;
    }
}
