package cn.hmst.sso.impl;

import cn.hmst.common.exception.ParamException;
import cn.hmst.common.util.MD5Util;
import cn.hmst.dao.SysUserMapper;
import cn.hmst.pojo.SysUser;
import cn.hmst.pojo.SysUserExample;
import cn.hmst.sso.service.LoginService;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/3/21 0021.
 */
@Service
public class loginServiceImpl implements LoginService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser LoginByUserNameAndPassword(String username, String password) throws ParamException{
        String errorMsg = "";
        if (StringUtils.isBlank(username)) {
            throw new ParamException("用户名不可以为空");
        }
        SysUser sysUser = null;
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            sysUser = list.get(0);
        }
        if (StringUtils.isBlank(password)) {
            throw new ParamException("密码不可以为空");
        } else if (sysUser == null) {
            throw new ParamException("查询不到指定的用户");
        } else if (!sysUser.getPassword().equals(MD5Util.encrypt(password))) {
            throw new ParamException("用户名或密码错误");
        } else if (sysUser.getStatus() != 1) {
            throw new ParamException("用户已被冻结，请联系管理员");
        } else {
            //登录成功
            return sysUser;
        }
    }
}
