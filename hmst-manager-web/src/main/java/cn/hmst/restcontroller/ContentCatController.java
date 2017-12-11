package cn.hmst.restcontroller;

import cn.hmst.common.pojo.EasyUITreeNode;
import cn.hmst.common.pojo.HmResult;
import cn.hmst.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Controller
public class ContentCatController {
    @Autowired
    private ContentCategoryService contentCategoryService;
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name="id",defaultValue ="0") long parentId){
        List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
        return  list;
    }
    @RequestMapping("/content/category/create")
    @ResponseBody
    public HmResult cretateContentCategory(Long parentId, String name){
        HmResult hmResult = contentCategoryService.addConentCategory(parentId,name);
        return  hmResult;
    }
}
