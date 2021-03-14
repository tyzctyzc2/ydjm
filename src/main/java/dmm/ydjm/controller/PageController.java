package dmm.ydjm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(methods = {RequestMethod.GET})
@RequestMapping("/page")
public class PageController {
    @GetMapping(value = "/main")
    public String mainListPage(Model model) {
        return "main_page";
    }
}
