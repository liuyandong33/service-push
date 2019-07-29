package build.dream.push.controllers;

import build.dream.common.api.ApiRest;
import build.dream.common.saas.domains.SocketClient;
import build.dream.common.utils.ApplicationHandler;
import build.dream.common.utils.MethodCaller;
import build.dream.push.models.message.PushModel;
import build.dream.push.services.SocketClientService;
import build.dream.push.utils.WebSocketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    private SocketClientService socketClientService;

    @RequestMapping(value = "/push")
    @ResponseBody
    public String push() throws Exception {
        Map<String, String> requestParameters = ApplicationHandler.getRequestParameters();
        MethodCaller methodCaller = () -> {
            PushModel pushModel = ApplicationHandler.instantiateObject(PushModel.class, ApplicationHandler.getRequestParameters());
            pushModel.validateAndThrow();
            String target = pushModel.getTarget();
            String targetValue = pushModel.getTargetValue();
            String body = pushModel.getBody();

            TextMessage textMessage = new TextMessage(body);
            List<SocketClient> socketClients = socketClientService.obtainAllSocketClients(target, targetValue);
            for (SocketClient socketClient : socketClients) {
                WebSocketUtils.sendMessage(socketClient.getSessionId(), textMessage);
            }
            return ApiRest.builder().message("推送消息成功！").successful(true).build();
        };

        return ApplicationHandler.callMethod(methodCaller, "推送消息失败", requestParameters);
    }
}
