package cn.hmst.service;

import cn.hmst.dto.AclModuleLevelDto;
import cn.hmst.dto.SysDeptDto;
import cn.hmst.dto.SysRoleDto;

import java.util.List;

/**
 * Created by hm on 2017/12/23.
 */
public interface SysTreesService {
    public List<SysDeptDto> deptTree();
    public List<AclModuleLevelDto> aclModuleTree();
    public List<AclModuleLevelDto> roleTree(int roleId);
    public List<SysRoleDto> roleUserTree(int selectUserId,int userId);
}
