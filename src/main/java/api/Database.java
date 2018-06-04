package api;

import java.sql.SQLException;
import java.util.List;

public interface Database {

  boolean openConnection() throws SQLException, ClassNotFoundException;

  List<List<String>> executeSelectQuery(String sql) throws SQLException;

  boolean executeInsertQuery(String sql) throws SQLException;

  boolean closeConnection() throws SQLException;

}
