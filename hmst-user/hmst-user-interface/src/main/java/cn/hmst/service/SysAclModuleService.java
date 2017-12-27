package cn.hmst.service;

import cn.hmst.param.AclModuleParam;

/**
 * Created by hm on 2017/12/27.
 */
public interface SysAclModuleService {
    public void save(AclModuleParam param);
    public void update(AclModuleParam param);
    public void delete(int aclModuleId);
}
