package cn.hmst.service;

import cn.hmst.common.exception.ParamException;
import cn.hmst.param.SearchLogParam;
import cn.hmst.pojo.*;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
public interface SysLogService {
    PageResult<SysLogWithBLOBs> searchPageList(SearchLogParam param, PageQuery page) throws ParamException;

    void recover(int id) throws  ParamException;

    void saveDeptLog(SysDept before, SysDept after,Integer status);

    void saveUserLog(SysUser before, SysUser after,Integer status);

    void saveAclModuleLog(SysAclModule before, SysAclModule after,Integer status);

    void saveAclLog(SysAcl before, SysAcl after,Integer status);

    void saveRoleLog(SysRole before, SysRole after,Integer status);

    void saveRoleUserLog(int userId, List<Integer> before, List<Integer> after,Integer status);

    void saveRoleAclLog(int roleId, List<Integer> before, List<Integer> after,Integer status);
}
