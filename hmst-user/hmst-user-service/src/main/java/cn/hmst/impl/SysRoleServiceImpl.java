package cn.hmst.impl;

import cn.hmst.common.exception.ParamException;
import cn.hmst.common.util.BeanValidator;
import cn.hmst.dao.SysRoleAclMapper;
import cn.hmst.dao.SysRoleMapper;
import cn.hmst.dao.SysRoleUserMapper;
import cn.hmst.dao.SysUserMapper;
import cn.hmst.param.RoleParam;
import cn.hmst.pojo.SysRole;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;
import cn.hmst.service.SysLogService;
import cn.hmst.service.SysRoleService;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    @Resource
    private SysRoleAclMapper sysRoleAclMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysLogService sysLogService;

    @Override
    public void save(RoleParam param) throws  ParamException{
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole role = SysRole.builder().name(param.getName()).status(param.getStatus()).type(param.getType())
                .remark(param.getRemark()).build();
        role.setOperator("admin");
        role.setOperatorIp("127.0.0.1");
        role.setOperatorTime(new Date());
        sysRoleMapper.insertSelective(role);
        sysLogService.saveRoleLog(null, role,0);
    }

    @Override
    public void update(RoleParam param) throws  ParamException{
        BeanValidator.check(param);
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole before = sysRoleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的角色不存在");

        SysRole after = SysRole.builder().id(param.getId()).name(param.getName()).status(param.getStatus()).type(param.getType())
                .remark(param.getRemark()).build();
        after.setOperator("admnin");
        after.setOperatorIp("127.0.0.1");
        after.setOperatorTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(after);
        sysLogService.saveRoleLog(before, after,0);
    }

    @Override
    public PageResult<SysRole> getAll(String keyword, PageQuery pageQuery) throws  ParamException{
        BeanValidator.check(pageQuery);
        int count = sysRoleMapper.countByName(keyword,null);
        if (count > 0) {
            List<SysRole> list = sysRoleMapper.getAll(keyword,pageQuery);
            return PageResult.<SysRole>builder().total(count).data(list).build();
        }
        return PageResult.<SysRole>builder().build();
    }

    private boolean checkExist(String name, Integer id) {

        return sysRoleMapper.countByNameAndId(name, id) > 0;
    }

   /* public List<SysRole> getRoleListByUserId(int userId) {
        List<Integer> roleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        return sysRoleMapper.getByIdList(roleIdList);
    }


    @Override
    public List<SysRole> getRoleListByAclId(int aclId) {
        List<Integer> roleIdList = sysRoleAclMapper.getRoleIdListByAclId(aclId);
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        return sysRoleMapper.getByIdList(roleIdList);
    }

    @Override
    public List<SysUser> getUserListByRoleList(List<SysRole> roleList) {
        if (CollectionUtils.isEmpty(roleList)) {
            return Lists.newArrayList();
        }
        List<Integer> roleIdList = roleList.stream().map(role -> role.getId()).collect(Collectors.toList());
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleIdList(roleIdList);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserMapper.getByIdList(userIdList);
    }*/
}
