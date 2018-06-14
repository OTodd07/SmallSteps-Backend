package api;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LocationController {

  @MessageMapping("/confluence/{device_id}")
  @SendTo("/topic/confluence/{device_id}")
//  public Location sendPos(@Payload Location location) {
    public Location sendPos(Location location) {
    return location;
  }

  @MessageMapping("/request/{device_id}")
  @SendTo("/topic/confluence/{device_id}")
  public Request request(@Payload Request request) {
    return new Request(request.getSender());
  }

  @MessageMapping("/response/{device_id}")
  @SendTo("/topic/confluence/{device_id}")
  public Response response(@Payload Response response) {
    return response;
  }
}
