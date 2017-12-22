package cn.hmst.restcontroller;

import cn.hmst.comon.pojo.HmResult;
import cn.hmst.content.service.ContentService;
import cn.hmst.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/12/7 0007.
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping(value = "content/save",method = RequestMethod.POST)
    @ResponseBody
    public HmResult addContent(TbContent content){
        HmResult result =contentService.addContent(content);
        return result;
    }
}
