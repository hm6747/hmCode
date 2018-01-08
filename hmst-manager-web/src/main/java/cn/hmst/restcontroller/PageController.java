package cn.hmst.restcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex(){

        return  "redirect:/html/index.html";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
