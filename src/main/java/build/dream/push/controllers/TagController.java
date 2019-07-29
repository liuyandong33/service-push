package build.dream.push.controllers;

import build.dream.common.annotations.ApiRestAction;
import build.dream.push.models.account.SetTagModel;
import build.dream.push.services.SocketClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/tag")
public class TagController {
    @CrossOrigin(value = "*")
    @RequestMapping(value = "/setTag")
    @ResponseBody
    @ApiRestAction(modelClass = SetTagModel.class, serviceClass = SocketClientService.class, serviceMethodName = "setTag", error = "设置别名失败")
    public String setTag() {
        return null;
    }
}
