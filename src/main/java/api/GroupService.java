package api;

import entities.Group;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GroupService {

  private final PsqlDB db = new PsqlDB();

  public List<Group> getGroupsByDeviceId(String deviceId) throws SQLException, ClassNotFoundException {
    db.openConnection();
    String query = String.format("SELECT * FROM groups WHERE device_id = %s", deviceId);
    List<Group> groups = Group.fromString(db.executeSelectQuery(query));
    db.closeConnection();
    return groups;
  }


  public List<Group> getAllGroups() throws SQLException, ClassNotFoundException {
    db.openConnection();
    String query = String.format("SELECT * FROM groups");
    List<Group> groups = Group.fromString(db.executeSelectQuery(query));
    db.closeConnection();
    return groups;
  }

}
