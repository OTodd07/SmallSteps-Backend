import api.GreetingController;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GreetingControllerTests {

  @Test
  public void noArgumentsGivesHelloWorld() {
    GreetingController controller = new GreetingController();
    assertTrue(controller.greeting("hello").getContent().equals("Hello, HELLO!"));
  }

}
