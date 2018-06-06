package api;

import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public List<Group> get(@RequestParam("device_id") Optional<String> deviceId,
                         HttpServletResponse response) {
    List<Group> groups = new ArrayList<>();

    try {
      groups = deviceId.isPresent() ? groupService.getGroupsByDeviceId(deviceId.get())
                                  : groupService.getAllGroups();
      if (groups.isEmpty()) response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (SQLException | ClassNotFoundException exception) {
      response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

    return groups;
  }

  @PostMapping
  public ResponseEntity<?> post(@RequestBody Group group) {
    try {
      return new ResponseEntity<>(groupService.addNewGroup(group) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    } catch (SQLException | ClassNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }

  @PutMapping
  public ResponseEntity<?> put(@RequestParam(value = "walker_id") String walkerID,
                               @RequestParam(value = "group_id") String group_id) {
    try {
      return new ResponseEntity<>(groupService.joinGroup(walkerID, group_id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    } catch (SQLException | ClassNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }

}
