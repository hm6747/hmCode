package cn.hmst.dto;

import cn.hmst.pojo.SysRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
@Getter
@Setter
@ToString
public class SysRoleDto extends   SysRole{
    // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;

    public static SysRoleDto adapt(SysRole role) {
        SysRoleDto dto = new SysRoleDto();
        BeanUtils.copyProperties(role, dto);
        return dto;
    }
}
