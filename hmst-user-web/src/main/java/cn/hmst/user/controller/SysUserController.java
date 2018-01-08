package cn.hmst.user.controller;

import cn.hmst.common.pojo.JsonData;
import cn.hmst.param.UserParam;
import cn.hmst.pojo.SysUser;
import cn.hmst.query.PageQuery;
import cn.hmst.query.PageResult;
import cn.hmst.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hm on 2017/12/25.
 */
@Slf4j
@RequestMapping("/sys/user")
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(UserParam param) {
        sysUserService.save(param);
        return JsonData.success();
    }


    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateUser(UserParam param) {
        sysUserService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId,pageQuery);
        return JsonData.success(result);
    }
}
