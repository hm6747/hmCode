package cn.hmst.service;

import cn.hmst.common.exception.ParamException;
import cn.hmst.param.AclModuleParam;

/**
 * Created by hm on 2017/12/27.
 */
public interface SysAclModuleService {
    public void save(AclModuleParam param) throws  ParamException;
    public void update(AclModuleParam param) throws ParamException;
    public void delete(int aclModuleId) throws  ParamException;
}
