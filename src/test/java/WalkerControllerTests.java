import api.WalkerController;
import api.WalkerService;
//import api.WebAppContext;
import entities.Walker;
import org.junit.Before;
import org.junit.Test;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppTestContext.class)
@WebAppConfiguration
public class WalkerControllerTests {

  @Configuration
  static class Config{
    @Bean
    public WalkerService walkerService() {
      return new WalkerService();
    }
  }

  private MockMvc mockMvc;
  private Walker walker;

  @Mock
  private WalkerService walkerService;

  @InjectMocks
  private WalkerController controller;

  @Before
  public void setUp() {
    Mockito.reset(walkerService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    walker = new Walker("id","Walker","walker.jpg","123");
  }

  @Test
  public void findWalkerByIDShouldReturnsWalkerWithThatID() throws Exception {
    when(walkerService.findWalkerById("id")).thenReturn(Arrays.asList(walker));
    mockMvc.perform(get("/walker?device_id=id"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].device_id", is("id")))
            .andExpect(jsonPath("$[0].name", is("Walker")))
            .andExpect(jsonPath("$[0].picture", is("walker.jpg")))
            .andExpect(jsonPath("$[0].phone_number", is("123")));
  }

  @Test
  public void getRequestForNewWalkerReturnsNotFoundStatusCode() throws Exception {
    when(walkerService.findWalkerById("wrongId")).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/walker?device_id=wrongId"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void serviceUnavailableResponseOnDatabaseConnectionFaults() throws Exception {
    when(walkerService.findWalkerById("myDeviceId")).thenThrow(new SQLException());
    mockMvc.perform(get("/walker?device_id=myDeviceId"))
            .andExpect(status().isServiceUnavailable());
  }

}

