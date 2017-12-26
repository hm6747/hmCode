package cn.hmst.service;

import cn.hmst.param.RoleParam;
import cn.hmst.pojo.SysRole;

import java.util.List;

/**
 * Created by hm on 2017/12/27.
 */
public interface SysRoleService {
    public void save(RoleParam param);
    public void update(RoleParam param);
    public List<SysRole> getAll();
/*    public List<SysRole> getRoleListByUserId(int userId);
    public List<SysRole> getRoleListByAclId(int aclId);
    public List<SysUser> getUserListByRoleList(List<SysRole> roleList);*/
}
