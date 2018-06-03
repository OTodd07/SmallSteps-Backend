package api;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlDB implements Database {

  private static final String PASSWORD = "CgltOdrPo9";
  private static final String USERNAME = "g1727129_u";
  private static final String URL = "jdbc:postgresql://db.doc.ic.ac.uk:5432/g1727129_u";

  private final Properties properties = new Properties();
  private final String[] columnHeaders;
  private Connection conn = null;

  public PsqlDB(String[] columnHeaders) {
    this.columnHeaders = columnHeaders;
    properties.setProperty("user", USERNAME);
    properties.setProperty("password", PASSWORD);
  }

  @Override
  public boolean openConnection() {
    try {
      conn = DriverManager.getConnection(URL, properties);
    } catch (SQLException exception) {
      // TODO handle exception properly
      exception.printStackTrace();
      return false;
    }

    return true;
  }

  @Override
  public List<List<String>> executeSelectQuery(String sql) {
    List<List<String>> response = new ArrayList<>();
    try {
      ResultSet result = conn.createStatement().executeQuery(sql);
      while (result.next()) {
        List<String> row = new ArrayList<>();
        for (String header : columnHeaders) {
          row.add(result.getString(header));
          System.out.println(result.getString(header));
        }
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
