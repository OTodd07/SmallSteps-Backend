package api;

import entities.Walker;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class WalkerService {

  private final Database db = new PsqlDB();

  public List<Walker> findWalkerById(String deviceId) throws Exception {
    db.openConnection();

    String query = String.format("SELECT * FROM walkers WHERE device_id = '%s'", deviceId);
    List<Walker> walkers = Walker.fromString(db.executeSelectQuery(query));
    db.closeConnection();

    return walkers;
  }

  public boolean addNewWalker(Walker walker) throws SQLException {
    return walker.isValid();
  }

}
