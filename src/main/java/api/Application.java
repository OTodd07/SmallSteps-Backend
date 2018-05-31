package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
  private final String url = "jdbc:postgresql://db.doc.ic.ac.uk:5432/g1727129_u";
  private final String user = "g1727129_u";
  private final String password = "CgltOdrPo9";

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

  }
}
