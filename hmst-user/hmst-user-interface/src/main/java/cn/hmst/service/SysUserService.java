package cn.hmst.service;

import cn.hmst.common.exception.ParamException;
import cn.hmst.param.UserParam;
import cn.hmst.pojo.SysUser;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;

/**
 * Created by hm on 2017/12/25.
 */
public interface SysUserService {
    public void save(UserParam param) throws ParamException;
    public void update(UserParam param) throws  ParamException;
    public SysUser findByKeyword(String username);
    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page,String keyword) throws  ParamException;
    }
