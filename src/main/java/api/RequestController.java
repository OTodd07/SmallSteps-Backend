package api;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RequestController {

//  @MessageMapping("/request/{device_id}")
//  @SendTo("/topic/request/{device_id}")
//  public Request request(@Payload Request request, @DestinationVariable String device_id) {
//    return new Request(device_id);
//  }
}
