package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


import java.util.Properties;
import java.sql.*;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  private static void Connect() {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.err.println("Where is your PostgreSQL JDBC Driver? "
              + "Include in your library path!");
      e.printStackTrace();
    }
    String url = "jdbc:postgresql://db.doc.ic.ac.uk:5432/g1727129_u";
    Properties props = new Properties();
    props.setProperty("user", "g1727129_u");
    props.setProperty("password", "CgltOdrPo9");
    try {
      Connection conn = DriverManager.getConnection(url, props);

      // space to add statements
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


}
