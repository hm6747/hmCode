package cn.hmst.user.controller;

import cn.hmst.comon.pojo.JsonData;
import cn.hmst.dto.SysDeptDto;
import cn.hmst.param.DeptParam;
import cn.hmst.service.SysDeptService;
import cn.hmst.service.SysTreesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hm on 2017/12/23.
 */
@Slf4j
@RequestMapping("/sys/dept")
@Controller
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysTreesService sysTreesService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param) {
        sysDeptService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<SysDeptDto> dtoList = sysTreesService.deptTree();
        return JsonData.success(dtoList);
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateDept(DeptParam param) {
        sysDeptService.save(param);
        return JsonData.success();
    }
}
