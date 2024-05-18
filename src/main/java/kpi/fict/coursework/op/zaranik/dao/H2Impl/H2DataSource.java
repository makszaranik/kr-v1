package kpi.fict.coursework.op.zaranik.dao.H2Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//HikaryCP сделать
public class H2DataSource {
  private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
  private static final String INIT_SQL = "RUNSCRIPT FROM 'classpath:database.sql'";
  private static boolean initialized = false;

  static {
    try {
      Class.forName("org.h2.Driver");
      System.out.println("H2 Driver loaded successfully.");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to load H2 driver.", e);
    }
  }

  public static synchronized Connection getConnection() {
    if (!initialized) {
      initializeDatabase();
      initialized = true;
    }
    try {
      return DriverManager.getConnection(JDBC_URL);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to get connection.", e);
    }
  }

  private static void initializeDatabase() {
    try (Connection connection = DriverManager.getConnection(JDBC_URL);
        Statement stmt = connection.createStatement()) {
      stmt.execute(INIT_SQL);
      System.out.println("Database initialized successfully.");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to initialize database.", e);
    }
  }
}
