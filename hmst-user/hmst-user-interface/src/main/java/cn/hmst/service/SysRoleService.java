package cn.hmst.service;

import cn.hmst.param.RoleParam;
import cn.hmst.pojo.SysRole;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;

/**
 * Created by hm on 2017/12/27.
 */
public interface SysRoleService {
    public void save(RoleParam param);
    public void update(RoleParam param);
    public PageResult<SysRole> getAll(String keyword, PageQuery pageQuery);
/*    public List<SysRole> getRoleListByUserId(int userId);
    public List<SysRole> getRoleListByAclId(int aclId);
    public List<SysUser> getUserListByRoleList(List<SysRole> roleList);*/
}
