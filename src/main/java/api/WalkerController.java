package api;

import entities.Walker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/walker")
public class WalkerController {

  @Autowired
  private WalkerService walkerService;

  @GetMapping
  @ResponseBody
  public List<Walker> get(@RequestParam(value = "device_id") String deviceId,
                          HttpServletResponse response) {
    List<Walker> walkers = new ArrayList<>();
    try {
      walkers = walkerService.findWalkerById(deviceId);
      if (walkers.size() == 0) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
    } catch (SQLException | ClassNotFoundException e) {
      response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

    return walkers;
  }

  @GetMapping("/name")
  @ResponseBody
  public String getNameFromId(@RequestParam(value = "device_id") String deviceId, HttpServletResponse response) {
    String name = "";
    try {
      name = walkerService.getNameFromId(deviceId);
      if (name.equals(null)) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
    } catch (SQLException | ClassNotFoundException e) {
      response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

    return name;
  }

  @PostMapping
  public ResponseEntity<?> post(@RequestBody Walker walker) {
    try {
      boolean status = walkerService.findWalkerById(walker.getDevice_id()).isEmpty();
      if (!status) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

      status = walkerService.addNewWalker(walker);
      return new ResponseEntity<>(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    } catch (SQLException | ClassNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }




}
