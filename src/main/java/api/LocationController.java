package api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LocationController {

  @MessageMapping("/confluence/{device_id}")
  @SendTo("/topic/confluence/{device_id}")
  public Location sendPos(@Payload Location location) {
    return location;
  }
}
