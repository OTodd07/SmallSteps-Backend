import api.GroupService;
import api.GroupsController;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Group;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppTestContext.class)
@WebAppConfiguration
public class GroupControllerTests {

  @Configuration
  static class Config {
    @Bean
    public GroupService groupService() {
      return new GroupService();
    }
  }

  private MockMvc mockMvc;
  private Group group;
  private Group badGroup;

  @Mock
  private GroupService groupService;

  @InjectMocks
  private GroupsController controller;

  @Before
  public void setUp() {
    Mockito.reset(groupService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    group = new Group("id", "", "2018-06-10 12:00:00", "wpefhflaimcbcypsygywqqyutvtxvbhpdnlb",
            "51.4989", "51.4989", "01:00:00", false, false, "");
    badGroup = new Group("", "", "", "", "", "", ""
            , false, false, "");
  }

  @Test
  public void getRequestByIDWhenGroupNotInTheDBReturnsNotFoundResponse() throws Exception {
    when(groupService.getGroupsByDeviceId("id")).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/groups?device_id=id"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void getRequestForAllGroupsWhenNoGroupsInTheDBReturnsNotFoundResponse() throws Exception {
    when(groupService.getAllGroups("","","")).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/groups?latitude=0&longitude=0"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void getRequestForGroupInTheDBWhenPresentReturnsOkResponse() throws Exception {
    when(groupService.getGroupsByDeviceId("wpefhflaimcbcypsygywqqyutvtxvbhpdnlb"))
            .thenReturn(Collections.singletonList(group));

//    mockMvc.perform(get("/groups?device_id=wpefhflaimcbcypsygywqqyutvtxvbhpdnlb&latitude=0&longitude=0"))
    mockMvc.perform(get("/groups?device_id=wpefhflaimcbcypsygywqqyutvtxvbhpdnlb"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is("id")))
            .andExpect(jsonPath("$[0].name", is("")))
            .andExpect(jsonPath("$[0].time", is("2018-06-10 12:00:00")))
            .andExpect(jsonPath("$[0].admin_id", is("wpefhflaimcbcypsygywqqyutvtxvbhpdnlb")))
            .andExpect(jsonPath("$[0].location_latitude", is("51.4989")))
            .andExpect(jsonPath("$[0].location_longitude", is("51.4989")))
            .andExpect(jsonPath("$[0].duration", is("01:00:00")))
            .andExpect(jsonPath("$[0].has_dogs", is(false)))
            .andExpect(jsonPath("$[0].has_kids", is(false)))
            .andExpect(jsonPath("$[0].description", is("")));
  }

  @Test
  public void getRequestWithDeviceIDAndLatAndLongReturnsBadRequest() throws Exception {
    mockMvc.perform(get("/groups?device_id=wpefhflaimcbcypsygywqqyutvtxvbhpdnlb&latitude=0&longitude=0"))
            .andExpect(status().isBadRequest());

  }

  @Test
  public void getRequestForAllGroupsInTheDBWhenGroupsPresentReturnsOkResponse() throws Exception {
    when(groupService.getAllGroups("0","0","1.5")).thenReturn(Arrays.asList(group));
    mockMvc.perform(get("/groups?latitude=0&longitude=0"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is("id")))
            .andExpect(jsonPath("$[0].name", is("")))
            .andExpect(jsonPath("$[0].time", is("2018-06-10 12:00:00")))
            .andExpect(jsonPath("$[0].admin_id", is("wpefhflaimcbcypsygywqqyutvtxvbhpdnlb")))
            .andExpect(jsonPath("$[0].location_latitude", is("51.4989")))
            .andExpect(jsonPath("$[0].location_longitude", is("51.4989")))
            .andExpect(jsonPath("$[0].duration", is("01:00:00")))
            .andExpect(jsonPath("$[0].has_dogs", is(false)))
            .andExpect(jsonPath("$[0].has_kids", is(false)))
            .andExpect(jsonPath("$[0].description", is("")));
  }

  @Test
  public void returnsBadRequestIfGroupToBeAddedIsInvalid() throws Exception {
    when(groupService.addNewGroup(badGroup)).thenReturn(badGroup.groupValidityCheck());
    mockMvc.perform(post("/groups")
            .content(asJsonString(badGroup))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void returnsOkStatusIfJoinRequestIsValid() throws Exception {
    when(groupService.joinGroup("w_id", "g_id")).thenReturn(true);
    mockMvc.perform(put("/groups?walker_id=w_id&group_id=g_id"))
            .andExpect(status().isOk());
  }

  @Test
  public void returnsBadRequestIfJoinRequestIsInvalid() throws Exception {
    when(groupService.joinGroup("w_id", "g_id")).thenReturn(false);
    mockMvc.perform(put("/groups?walker_id=w_id&group_id=g_id"))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void returnsOkStatusIfGroupToBeAddedIsValid() throws Exception {
    when(groupService.addNewGroup(group)).thenReturn(group.groupValidityCheck());
    mockMvc.perform(post("/groups")
            .content(asJsonString(group))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }


  @Test
  public void returnsBadRequestIfDeleteRequestIsInvalid() throws Exception {
    when(groupService.deleteFromGroup("w_id", "g_id")).thenReturn(false);
    mockMvc.perform(delete("/groups?walker_id=w_id&group_id=g_id"))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void returnsOkStatusIfDeleteRequestIsValid() throws Exception {
    when(groupService.deleteFromGroup("w_id", "g_id")).thenReturn(true);
    mockMvc.perform(delete("/groups?walker_id=w_id&group_id=g_id"))
            .andExpect(status().isOk());
  }


  private static String asJsonString(final Object obj) {
    String jsonContent = "";
    try {
      ObjectMapper mapper = new ObjectMapper();
      jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception exception) {
      // Do nothing
    }

    return jsonContent;
  }

}
