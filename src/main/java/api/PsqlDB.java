package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlDB implements Database {

  private static final String PASSWORD = "CgltOdrPo9";
  private static final String USERNAME = "g1727129_u";
  private static final String URL = "jdbc:postgresql://db.doc.ic.ac.uk:5432/g1727129_u";

  private final Properties properties = new Properties();
  private Connection conn = null;

  public PsqlDB() {
    properties.setProperty("user", USERNAME);
    properties.setProperty("password", PASSWORD);
  }

  @Override
  public boolean openConnection() throws SQLException, ClassNotFoundException {
    Class.forName("org.postgresql.Driver");
    conn = DriverManager.getConnection(URL, properties);
    return true;
  }

  @Override
  public List<List<String>> executeSelectQuery(String sql) {
    List<List<String>> response = new ArrayList<>();
    try {
      ResultSet result = conn.createStatement().executeQuery(sql);
      while (result.next()) {
        List<String> row = new ArrayList<>();
        ResultSetMetaData metaData = result.getMetaData();
        int colCount = metaData.getColumnCount();
        for (int i = 1; i <= colCount; ++i) row.add(metaData.getColumnName(i));
        response.add(row);
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return response;
  }

  @Override
  public boolean closeConnection() {
    try {
      conn.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
      return false;
    }
    return true;
  }

}
