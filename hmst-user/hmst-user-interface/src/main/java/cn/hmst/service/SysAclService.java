package cn.hmst.service;

import cn.hmst.common.exception.ParamException;
import cn.hmst.param.AclParam;
import cn.hmst.pojo.SysAcl;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;

/**
 * Created by hm on 2017/12/27.
 */
public interface SysAclService {
    public void save(AclParam param) throws ParamException;
    public void update(AclParam para) throws  ParamException;
    public PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageQuery page,String keyword) throws  ParamException;
}
