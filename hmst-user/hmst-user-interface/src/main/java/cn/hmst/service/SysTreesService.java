package cn.hmst.service;

import cn.hmst.dto.AclModuleLevelDto;
import cn.hmst.dto.SysDeptDto;

import java.util.List;

/**
 * Created by hm on 2017/12/23.
 */
public interface SysTreesService {
    public List<SysDeptDto> deptTree();
    public List<AclModuleLevelDto> aclModuleTree();
}
