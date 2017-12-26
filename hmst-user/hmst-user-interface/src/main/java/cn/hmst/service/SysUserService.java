package cn.hmst.service;

import cn.hmst.param.UserParam;
import cn.hmst.pojo.SysUser;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;

/**
 * Created by hm on 2017/12/25.
 */
public interface SysUserService {
    public void save(UserParam param);
    public void update(UserParam param);
    public SysUser findByKeyword(String username);
    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page);
    }
