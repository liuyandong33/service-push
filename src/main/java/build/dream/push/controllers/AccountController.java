package build.dream.push.controllers;

import build.dream.common.annotations.ApiRestAction;
import build.dream.push.models.account.SetAccountModel;
import build.dream.push.services.SocketClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @CrossOrigin(value = "*")
    @RequestMapping(value = "/setAccount")
    @ResponseBody
    @ApiRestAction(modelClass = SetAccountModel.class, serviceClass = SocketClientService.class, serviceMethodName = "setAccount", error = "设置账号失败")
    public String setAccount() {
        return null;
    }
}
