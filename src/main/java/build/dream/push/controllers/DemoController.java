package build.dream.push.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
    @RequestMapping(value = "/index")
    public String index() {
        return "demo/index";
    }
}
