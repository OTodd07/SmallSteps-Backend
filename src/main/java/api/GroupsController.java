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

  @GetMapping()
  @ResponseBody
  public List<Group> get(@RequestParam("device_id") Optional<String> deviceId, @RequestParam("latitude") Optional<String> latitude,
                         @RequestParam("longitude") Optional<String> longitude
                         , @RequestParam(value = "radius",defaultValue = "1.5") Optional<String> radius,
                         HttpServletResponse response) {
    List<Group> groups = new ArrayList<>();

    try {
      if (deviceId.isPresent()) {

        if (latitude.isPresent() || longitude.isPresent()) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
          groups = groupService.getGroupsByDeviceId(deviceId.get());
          if (groups.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
          }
        }

      } else {
        if (!(latitude.isPresent() && longitude.isPresent())) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
          groups = groupService.getAllGroups(latitude.get(),longitude.get(), radius.get());
          if (groups.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
          }
        }
      }
      } catch(SQLException | ClassNotFoundException exception){
        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
      }

    return groups;
  }

  @GetMapping("/admin")
  @ResponseBody
  public String getAdmin(@RequestParam("group_id") String group_id, HttpServletResponse response) {
    String res = "";
    try {
      res = groupService.getAdmin(group_id);
      if (res.equals("")) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
    } catch (SQLException | ClassNotFoundException e) {
      response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

    return res;

  }

  @GetMapping("/name")
  @ResponseBody
  public String getName(@RequestParam("group_id") String group_id, HttpServletResponse response) {
    String res = "";
    try {
      res = groupService.getName(group_id);
      if (res.equals("")) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
    } catch (SQLException | ClassNotFoundException e) {
      response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }
    return res;
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

  @DeleteMapping
  public ResponseEntity<?> delete(@RequestParam(value = "walker_id") String walkerID,
                                  @RequestParam(value = "group_id" ) String group_id) {

    try {
      return new ResponseEntity<>(groupService.deleteFromGroup(walkerID, group_id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    } catch (SQLException | ClassNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
