package cn.hmst.service;

import cn.hmst.param.AclParam;
import cn.hmst.pojo.SysAcl;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;

/**
 * Created by hm on 2017/12/27.
 */
public interface SysAclService {
    public void save(AclParam param);
    public void update(AclParam para);
    public PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageQuery page);
}
