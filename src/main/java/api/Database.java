package api;

import java.sql.SQLException;
import java.util.List;

public interface Database {

  boolean openConnection() throws SQLException;
  List<List<String>> executeSelectQuery(String sql);
  boolean closeConnection();

}
