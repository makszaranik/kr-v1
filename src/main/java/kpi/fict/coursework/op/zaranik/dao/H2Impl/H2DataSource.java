package kpi.fict.coursework.op.zaranik.dao.H2Impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class H2DataSource {
  private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
  private static final String INIT_SQL = "RUNSCRIPT FROM 'classpath:database.sql'";
  private static boolean initialized = false;
  private static HikariDataSource dataSource;

  static {
    try {
      Class.forName("org.h2.Driver");
      System.out.println("H2 Driver loaded successfully.");

      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(JDBC_URL);
      config.setDriverClassName("org.h2.Driver");
      config.setMaximumPoolSize(10);
      config.setMinimumIdle(2);
      config.setInitializationFailTimeout(-1);

      dataSource = new HikariDataSource(config);
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
      return dataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to get connection.", e);
    }
  }

  private static void initializeDatabase() {
    try (Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement()) {
      stmt.execute(INIT_SQL);
      System.out.println("Database initialized successfully.");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to initialize database.", e);
    }
  }
}
