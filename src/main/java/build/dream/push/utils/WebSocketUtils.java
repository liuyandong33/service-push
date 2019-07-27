package build.dream.push.utils;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtils {
    private static final ConcurrentHashMap<String, WebSocketSession> SESSION_ID_WEB_SOCKET_SESSION_MAP = new ConcurrentHashMap<String, WebSocketSession>();

    public static void addWebSocketSession(String sessionId, WebSocketSession webSocketSession) {
        SESSION_ID_WEB_SOCKET_SESSION_MAP.put(sessionId, webSocketSession);
    }

    public static void removeWebSocketSession(String sessionId) {
        SESSION_ID_WEB_SOCKET_SESSION_MAP.remove(sessionId);
    }

    public static void sendMessage(String sessionId, TextMessage textMessage) throws IOException {
        WebSocketSession webSocketSession = SESSION_ID_WEB_SOCKET_SESSION_MAP.get(sessionId);
        webSocketSession.sendMessage(textMessage);
    }
}
