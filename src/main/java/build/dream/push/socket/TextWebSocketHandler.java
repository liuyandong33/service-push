package build.dream.push.socket;

import build.dream.push.services.SocketClientService;
import build.dream.push.utils.WebSocketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class TextWebSocketHandler extends org.springframework.web.socket.handler.TextWebSocketHandler {
    @Autowired
    private SocketClientService socketClientService;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Map<String, Object> attributes = session.getAttributes();
        HttpSession httpSession = (HttpSession) attributes.get("httpSession");
        String sessionId = httpSession.getId();
        WebSocketUtils.removeWebSocketSession(sessionId);
        socketClientService.deleteSocketClient(sessionId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Map<String, Object> attributes = session.getAttributes();
        HttpSession httpSession = (HttpSession) attributes.get("httpSession");
        String sessionId = httpSession.getId();
        WebSocketUtils.addWebSocketSession(sessionId, session);
        socketClientService.saveSocketClient(sessionId);
    }
}
