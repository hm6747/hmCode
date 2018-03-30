package cn.hmst.restcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/12/4 0004.
 */
@Controller
public class indexController {
/*    @Autowired
    private ContentService contentService;*/
    @Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;

    @RequestMapping("/index.page")
    public String showIndex(Model model){
//        List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
//        model.addAttribute("ad1List",ad1List);
        return  "redirect:/pages/index.html";
    }
}
