package build.dream.push.controllers;

import build.dream.common.utils.ApplicationHandler;
import build.dream.push.constants.Constants;
import build.dream.push.utils.WebSocketUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @RequestMapping(value = "/push")
    @ResponseBody
    public String push() throws IOException {
        String sessionId = ApplicationHandler.getRequestParameter("sessionId");
        TextMessage textMessage = new TextMessage(UUID.randomUUID().toString());
        WebSocketUtils.sendMessage(sessionId, textMessage);
        return Constants.OK;
    }
}
