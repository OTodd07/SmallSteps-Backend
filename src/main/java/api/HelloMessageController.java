package api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

public class HelloMessageController {

  @MessageMapping("/notifyWalkers")
  @SendTo("/all/messages")
  public HelloMessage hello(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new HelloMessage("Hello, " + HtmlUtils.htmlEscape(message.getMessage()) + "!");
  }

}
