package api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;


@Component
public class WebSocketListener {

  private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);

  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  @EventListener
  public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    logger.info("Received a new web socket connection");
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
      logger.info("User Disconnected : " );

//    String username = (String) headerAccessor.getSessionAttributes().get("username");
//    if(username != null) {
//      logger.info("User Disconnected : " + username);
//
//      ChatMessage chatMessage = new ChatMessage();
//      chatMessage.setType(ChatMessage.MessageType.LEAVE);
//      chatMessage.setSender(username);
//
//      messagingTemplate.convertAndSend("/topic/public", chatMessage);
//    }
  }

  @EventListener
  public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
    logger.info("subscribedd");
  }
}
