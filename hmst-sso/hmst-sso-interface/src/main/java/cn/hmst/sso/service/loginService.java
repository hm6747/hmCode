package cn.hmst.sso.service;

import cn.hmst.common.exception.ParamException;
import cn.hmst.pojo.SysUser;

/**
 * Created by Administrator on 2018/3/21 0021.
 */
public interface LoginService {
     SysUser LoginByUserNameAndPassword(String UserName, String password) throws ParamException;
}
