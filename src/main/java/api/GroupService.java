package api;

import entities.Group;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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

  public List<Group> getAllGroups(String curLat, String curLong, String radius) throws SQLException, ClassNotFoundException {
    db.openConnection();
    List<Group> groups = Group.fromString(db.executeSelectQuery("SELECT * FROM groups"))
            .stream().filter(group -> group.distanceInMetres(curLat,curLong) / 1000 <= Double.parseDouble(radius) ).collect(Collectors.toList());
    db.closeConnection();
    return groups;
  }

  public boolean addNewGroup(Group group) throws SQLException, ClassNotFoundException {
    boolean status = true;
    if (!group.groupValidityCheck()) return false;

    db.openConnection();


    String lookForGroup = String.format("SELECT * FROM groups where admin_id = '%s' AND time = '%s'",
            group.getAdmin_id(), group.getTime());
    List<Group> duplicate = Group.fromString(db.executeSelectQuery(lookForGroup));
    if(!duplicate.isEmpty()) {
      return false;
    }

    String createGroup = String.format("INSERT into groups (name, time, admin_id, location_latitude, location_longitude, " +
                    "duration, has_dogs, has_kids) values ('%s', '%s', '%s', '%s', '%s', '%s', %b, %b, '%s)",
            group.getName(), group.getTime(), group.getAdmin_id(), group.getLocation_latitude(), group.getLocation_longitude(),
            group.getDuration(), group.isHas_dogs(), group.isHas_kids(), group.getDescription());
      status = db.executeInsertQuery(createGroup);
//    try {
//      status = db.executeInsertQuery(createGroup);
//    } catch (Exception e) {
//      System.out.println("First insert failed");
//      System.out.println(createGroup);
//      e.printStackTrace();
//    }
//
//    if (!status) {
//      String deleteGroup = String.format("DELETE from groups where id = '%s'", group.getId());
//      db.executeDeleteQuery(deleteGroup);
//      return false;
//    }

    String getGroup = String.format("SELECT * FROM groups WHERE admin_id = '%s' AND time = '%s'", group.getAdmin_id(), group.getTime());
    List<Group> groups = Group.fromString(db.executeSelectQuery(getGroup));

    group.setId(groups.get(0).getId());

    String addAdmin = String.format("INSERT into walkers_groups (walker_id, group_id) values ('%s','%s')",
            group.getAdmin_id(), group.getId());

    status = db.executeInsertQuery(addAdmin);

    db.closeConnection();


    return status;
  }

  public boolean joinGroup(String walkerID, String group_id) throws SQLException , ClassNotFoundException {
    boolean status;
    db.openConnection();
    String joinGroup = String.format("INSERT into walkers_groups(walker_id, group_id) values ('%s', '%s')"
                                       , walkerID, group_id);
    status = db.executeInsertQuery(joinGroup);
    db.closeConnection();
    return status;
  }

  public boolean deleteFromGroup(String walkerID, String group_id) throws SQLException , ClassNotFoundException {
    boolean status;
    db.openConnection();
    String delete = String.format("DELETE FROM walkers_groups WHERE group_id = '%s' AND walker_id = '%s'",walkerID
                                   , group_id);
    status = db.executeInsertQuery(delete);
    return status;
  }
}
