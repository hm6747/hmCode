package cn.hmst.restcontroller;

import cn.hmst.common.pojo.EasyUITreeNode;
import cn.hmst.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
  /*  @InitBinder
       public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        binder.registerCustomEditor(int.class, new IntEditor());
        binder.registerCustomEditor(long.class, new LongEditor());
        binder.registerCustomEditor(double.class, new DoubleEditor());
        binder.registerCustomEditor(float.class, new FloatEditor());
       }*/

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(name = "id",defaultValue = "0")Long parentId){
        System.out.println(1);
        List<EasyUITreeNode>  list = itemCatService.getItemCatList(parentId);
        return list;
    }
}
