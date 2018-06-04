package api;

import entities.Walker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class WalkerService {

  //private final Database db = new PsqlDB(new String[]{"device_id", "name", "picture", "phone_number"});
  private final Database db = new PsqlDB();

  public List<Walker> findWalkerById(String deviceId) throws Exception {
    db.openConnection();

    String query = String.format("SELECT * FROM walkers WHERE device_id = '%s'", deviceId);
    List<Walker> walkers = Walker.fromString(db.executeSelectQuery(query));
    db.closeConnection();

    return walkers;
  }
}
