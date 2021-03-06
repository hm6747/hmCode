package cn.hmst.user.controller;

import cn.hmst.common.pojo.JsonData;
import cn.hmst.common.util.StringUtil;
import cn.hmst.dto.SysRoleDto;
import cn.hmst.param.RoleParam;
import cn.hmst.pojo.SysRole;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;
import cn.hmst.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysTreesService sysTreeService;
    @Autowired
    private SysRoleAclService sysRoleAclService;
    @Resource
    private SysRoleUserService sysRoleUserService;
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("role.page")
    public ModelAndView page() {
        return new ModelAndView("role");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveRole(RoleParam param) {
        sysRoleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateRole(RoleParam param) {
        sysRoleService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public JsonData list(String keyword, PageQuery pageQuery) {
        PageResult<SysRole> result = sysRoleService.getAll(keyword,pageQuery);
        return JsonData.success(result);
    }

    @RequestMapping("/roleTree.json")
    @ResponseBody
    public JsonData roleTree(@RequestParam("roleId") int roleId) {
        return JsonData.success(sysTreeService.roleTree(roleId));
    }
    @RequestMapping("/changeAcls.json")
    @ResponseBody
    public JsonData changeAcls(@RequestParam("roleId") int roleId, @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds) {
        List<Integer> aclIdList = StringUtil.strSplitToList(aclIds,",");
        sysRoleAclService.changeRoleAcls(roleId, aclIdList);
        return JsonData.success();
    }
    @RequestMapping("/roleUserTree.json")
    @ResponseBody
    public JsonData roleUserTree(@RequestParam("userId") int selectUserId) {
        int userId = 2;
        List<SysRoleDto> list = sysTreeService.roleUserTree(selectUserId,userId);
        return JsonData.success(list);
    }

    @RequestMapping("/changeUserRoles.json")
    @ResponseBody
    public JsonData changeUserRoles(@RequestParam("userId") int userId, @RequestParam(value = "roleIds", required = false, defaultValue = "") String roleIds) {
        List<Integer> roleIdList = StringUtil.strSplitToList(roleIds,",");
        sysRoleUserService.changeUserRoles(userId,roleIdList);
        return JsonData.success();
    }
  /*

    @RequestMapping("/changeUsers.json")
    @ResponseBody
    public JsonData changeUsers(@RequestParam("roleId") int roleId, @RequestParam(value = "userIds", required = false, defaultValue = "") String userIds) {
        List<Integer> userIdList = StringUtil.splitToListInt(userIds);
        sysRoleUserService.changeRoleUsers(roleId, userIdList);
        return JsonData.success();
    }

    @RequestMapping("/users.json")
    @ResponseBody
    public JsonData users(@RequestParam("roleId") int roleId) {
        List<SysUser> selectedUserList = sysRoleUserService.getListByRoleId(roleId);
        List<SysUser> allUserList = sysUserService.getAll();
        List<SysUser> unselectedUserList = Lists.newArrayList();

        Set<Integer> selectedUserIdSet = selectedUserList.stream().map(sysUser -> sysUser.getId()).collect(Collectors.toSet());
        for(SysUser sysUser : allUserList) {
            if (sysUser.getStatus() == 1 && !selectedUserIdSet.contains(sysUser.getId())) {
                unselectedUserList.add(sysUser);
            }
        }
        // selectedUserList = selectedUserList.stream().filter(sysUser -> sysUser.getStatus() != 1).collect(Collectors.toList());
        Map<String, List<SysUser>> map = Maps.newHashMap();
        map.put("selected", selectedUserList);
        map.put("unselected", unselectedUserList);
        return JsonData.success(map);
    }*/
}
