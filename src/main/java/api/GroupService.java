package api;

import entities.Group;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GroupService {

  public List<Group> getGroupsByDeviceId(String deviceId) throws SQLException, ClassNotFoundException {
    return null;
  }


  public List<Group> getAllGroups() throws SQLException, ClassNotFoundException {
    return null;
  }

}
