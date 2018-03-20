package cn.hmst.service;

import cn.hmst.pojo.SysUser;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
public interface SysRoleUserService {
     List<SysUser> getListByRoleId(int roleId);
     void changeUserRoles(int userId,List<Integer> roleIdList);
}
