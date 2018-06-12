package api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

public class TextController {


  @MessageMapping("/backEnd")
  @SendTo("/frontEnd")
  public Text sendText(@Payload Text text) {
    return text;
  }


}
