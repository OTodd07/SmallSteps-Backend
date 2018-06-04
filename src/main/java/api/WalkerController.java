package api;

import entities.Walker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

    return walkers;
  }

  @PostMapping
  public ResponseEntity<String> post() {
    ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
    return response;
  }

}
