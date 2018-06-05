package api;

import entities.Group;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GroupService {

  private final Database db = new PsqlDB();

  public List<Group> getGroupsByDeviceId(String deviceId) throws SQLException, ClassNotFoundException {
    db.openConnection();

    String query = String.format("SELECT groups.* FROM walkers_groups " +
            "INNER JOIN walkers ON walkers.device_id = walkers_groups.walker_id " +
            "INNER JOIN groups ON groups.id = walkers_groups.group_id WHERE walkers.device_id = '%s'", deviceId);

    List<Group> groups = Group.fromString(db.executeSelectQuery(query));
    db.closeConnection();
    return groups;
  }

  public List<Group> getAllGroups() throws SQLException, ClassNotFoundException {
    db.openConnection();
    List<Group> groups = Group.fromString(db.executeSelectQuery("SELECT * FROM groups"));
    db.closeConnection();
    return groups;
  }

  public boolean addNewGroup(Group group) throws SQLException, ClassNotFoundException {
    boolean status;
    if(!group.isValid()) {
      return false;
    }
    db.openConnection();
    String createGroup = String.format("INSERT into groups (id , name, time, admin_id, location_latitude, location_longitude, " +
            "duration, has_dogs, has_kids) values (%s, %s, %s, %s, %s, %s, %s, %b, %b)", group.getId(),
             group.getName(), group.getTime(), group.getAdmin_id(), group.getLocation_latitude(),group.getLocation_longitude(),
             group.getDuration(),group.isHas_dogs(),group.isHas_kids());
    status = db.executeInsertQuery(createGroup);

    if(!status) {
      String deleteGroup = String.format("DELETE from groups where id = %s", group.getId());
      db.executeDeleteQuery(deleteGroup);
      return false;
    }

    String addAdmin = String.format("Insert into walkers_groups (walker_id, group_id) values (%s,%s)",
            group.getAdmin_id(),group.getId());

    status = db.executeInsertQuery(addAdmin);

    db.closeConnection();


    return status;
  }
}
