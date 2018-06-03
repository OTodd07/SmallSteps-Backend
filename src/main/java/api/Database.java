package api;

import java.util.List;

public interface Database {

  boolean openConnection();
  List<List<String>> executeSelectQuery(String sql);
  boolean closeConnection();

}
