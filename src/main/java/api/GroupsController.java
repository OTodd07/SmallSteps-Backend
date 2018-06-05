package api;

import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupsController {

  @Autowired
  private GroupService groupService;

  @GetMapping
  @ResponseBody
  public List<Group> get(@RequestParam("deviceId") Optional<String> deviceId,
                         HttpServletResponse response) {
    List<Group> groups = new ArrayList<>();

    try {
      groups = deviceId.isPresent() ? groupService.getGroupsByDeviceId(deviceId.get())
              : groupService.getAllGroups();
    } catch (SQLException | ClassNotFoundException exception) {
      response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

    return groups;
  }

}
