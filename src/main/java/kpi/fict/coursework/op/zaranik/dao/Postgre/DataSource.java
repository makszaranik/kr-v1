package kpi.fict.coursework.op.zaranik.dao.Postgre;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DataSource {
  private static final String JDBC_URL = "jdbc:postgresql://localhost:5435/postgres";
  private static final String USER = "postgres";
  private static final String PASSWORD = "zaranik";
  private static boolean initialized = false;
  private static HikariDataSource dataSource;

  static {
    try {
      Class.forName("org.postgresql.Driver");
      System.out.println("Postgresql Driver loaded successfully.");

      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(JDBC_URL);
      config.setUsername(USER);
      config.setPassword(PASSWORD);
      config.setDriverClassName("org.postgresql.Driver");
      config.setMaximumPoolSize(10);
      config.setMinimumIdle(2);
      config.setInitializationFailTimeout(-1);

      dataSource = new HikariDataSource(config);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to load Postgresql driver.", e);
    }
  }

  public static Connection getConnection() {
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
      String initSql = new String(Files.readAllBytes(Paths.get("src/main/resources/database.sql")));
      stmt.execute(initSql);
      System.out.println("Database initialized successfully.");
    } catch (SQLException | IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to initialize database.", e);
    }
  }
}
