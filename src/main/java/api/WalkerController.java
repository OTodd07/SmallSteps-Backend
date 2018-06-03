package api;

import entities.Walker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/walker")
public class WalkerController {

//  @GetMapping
//  public List<Walker> get(@RequestParam(value="device_id") String deviceId,
//                   HttpServletResponse response) {
//    List<Walker> walkers = new ArrayList<>();
//    PsqlDB db = new PsqlDB(new String[]{"device_id", "name", "picture", "phone_number"});
//    if (!db.openConnection()) return walkers;
//    String query = String.format("SELECT * FROM walkers WHERE device_id = '%s'", deviceId);
//    walkers = Walker.fromString(db.executeSelectQuery(query));
//
//    if (!db.closeConnection()) return walkers;
//
//    if (walkers.size() == 0) response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//    return walkers;
//  }

  @GetMapping
  public ResponseEntity<String> get(@RequestParam(value="device_id") String deviceId,
                          HttpServletResponse response) {
    PsqlDB db = new PsqlDB(new String[]{"device_id", "name", "picture", "phone_number"});
    if (!db.openConnection()) return new ResponseEntity<>("Open connection failed", HttpStatus.SERVICE_UNAVAILABLE);
    String query = String.format("SELECT * FROM walkers WHERE device_id = '%s'", deviceId);
    List<Walker> walkers = Walker.fromString(db.executeSelectQuery(query));

    if (!db.closeConnection()) return new ResponseEntity<>("Close connection failed", HttpStatus.SERVICE_UNAVAILABLE);

    if (walkers.size() == 0) return new ResponseEntity<>("NO results", HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(walkers.get(0).toString(), HttpStatus.OK);
  }

}
