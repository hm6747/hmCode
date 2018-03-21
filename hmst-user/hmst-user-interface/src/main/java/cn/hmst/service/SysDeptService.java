package cn.hmst.service;

import cn.hmst.common.exception.ParamException;
import cn.hmst.param.DeptParam;

/**
 * Created by hm on 2017/12/23.
 */
public interface SysDeptService {
    public  void save(DeptParam param) throws ParamException;
    public  void upDateDept(DeptParam param) throws  ParamException;
}
