package api;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TextController {


  @MessageMapping("/backEnd/{test_id}")
  @SendTo("/topic/frontEnd/{test_id}")
  public Text sendText(@Payload Text text, @DestinationVariable String test_id) {

    System.out.println(text.getText() + "with testid: " + test_id);
    return text;
  }


}
