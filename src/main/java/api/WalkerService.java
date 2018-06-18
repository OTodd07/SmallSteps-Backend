package api;

import entities.Walker;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class WalkerService {

  private final Database db = new PsqlDB();

  public List<Walker> findWalkerById(String deviceId) throws SQLException, ClassNotFoundException {
    db.openConnection();

    String query = String.format("SELECT * FROM walkers WHERE device_id = '%s'", deviceId);
    List<Walker> walkers = Walker.fromString(db.executeSelectQuery(query));
    db.closeConnection();

    return walkers;
  }

  public boolean addNewWalker(Walker walker) throws SQLException, ClassNotFoundException {
    if (!walker.isValid()) return false;

    boolean status;
    db.openConnection();

    // Perform insert
    String insertQuery = String.format("INSERT INTO walkers (device_id, name, picture, phone_number)" +
            "VALUES('%s', '%s', '%s', '%s')", walker.getDevice_id(), walker.getName(),
            walker.getPicture(), walker.getPhone_number());
    status = db.executeInsertQuery(insertQuery);

    db.closeConnection();
    return status;
  }

  public String getNameFromId(String deviceId) throws SQLException, ClassNotFoundException {
    boolean status;
    db.openConnection();
    String getName = String.format("SELECT name from walkers where device_id = '%s'", deviceId);
    String res = db.executeSelectQuery(getName).get(0).get(0);
    db.closeConnection();
    return res;
  }

  public String getPhoneNumberFromId(String deviceID) throws SQLException, ClassNotFoundException {
    db.openConnection();
    String getName = String.format("SELECT name from walkers where phone_number = '%s'", deviceID);
    String res = db.executeSelectQuery(getName).get(0).get(0);
    db.closeConnection();
    return res;
  }

}
