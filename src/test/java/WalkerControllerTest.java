import api.Database;
import api.WalkerController;
import entities.Walker;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Configuration
class DatabaseConfig {

  @Bean
  public Database database() {
    return new Database() {
      @Override
      public boolean openConnection() {
        return true;
      }

      @Override
      public List<List<String>> executeSelectQuery(String sql) {
        return Arrays.asList(Arrays.asList("", "", "", ""));
      }

      @Override
      public boolean closeConnection() {
        return true;
      }
    };
  }
}

@ContextConfiguration(classes=DatabaseConfig.class)
public class WalkerControllerTest {

  @MockBean
  private HttpServletResponse response;

  @Test
  public void someTest() {
    WalkerController controller = new WalkerController();
    String testQuery = "irrelevant query";
    List<Walker> walkers = Arrays.asList(new Walker("", "", "", ""));
    assertEquals(controller.get(testQuery, response), walkers);
  }


}
