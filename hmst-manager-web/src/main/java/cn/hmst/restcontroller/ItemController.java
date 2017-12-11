package cn.hmst.restcontroller;

import cn.hmst.common.pojo.EasyUIDataGridResult;
import cn.hmst.common.pojo.HmResult;
import cn.hmst.pojo.TbItem;
import cn.hmst.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/10/30 0030.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return  tbItem;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult result =itemService.getItemList(page,rows);
        return  result;
    }

    @RequestMapping(value = "item/save",method = RequestMethod.POST)
    @ResponseBody
    public HmResult addItem(TbItem item,String desc){
        HmResult result = itemService.addItem(item,desc);
        return  result;
    }
}
