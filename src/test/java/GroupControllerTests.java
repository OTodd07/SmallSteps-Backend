import api.GroupService;
import api.GroupsController;
import entities.Group;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppTestContext.class)
@WebAppConfiguration
public class GroupControllerTests {


  @Configuration
  static class Config{
    @Bean
    public GroupService groupService() {
      return new GroupService();
    }
  }



  private MockMvc mockMvc;

  private Group group;

  @Mock
  private GroupService groupService;

  @InjectMocks
  private GroupsController controller;

  @Before
  public void setUp() {
    Mockito.reset(groupService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

}
