import api.GroupService;
import api.WalkerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppTestContext {

  @Bean
  public WalkerService walkerService() {
    return Mockito.mock(WalkerService.class);
  }

  @Bean
  public GroupService groupService() {return Mockito.mock(GroupService.class);}

}
