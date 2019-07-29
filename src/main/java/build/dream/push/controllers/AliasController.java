package build.dream.push.controllers;

import build.dream.common.annotations.ApiRestAction;
import build.dream.push.models.account.SetAliasModel;
import build.dream.push.services.SocketClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/alias")
public class AliasController {
    @CrossOrigin(value = "*")
    @RequestMapping(value = "/setAlias")
    @ResponseBody
    @ApiRestAction(modelClass = SetAliasModel.class, serviceClass = SocketClientService.class, serviceMethodName = "setAlias", error = "设置别名失败")
    public String setAlias() {
        return null;
    }
}
