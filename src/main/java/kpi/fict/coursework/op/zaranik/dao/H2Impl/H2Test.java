package kpi.fict.coursework.op.zaranik.dao.H2Impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kpi.fict.coursework.op.zaranik.dao.DaoFactory;
import kpi.fict.coursework.op.zaranik.dao.UserDao;
import kpi.fict.coursework.op.zaranik.model.Role.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;

public class H2Test {
  public static void main(String[] args) {
    try (Connection connection = H2DataSource.getConnection()) {

      H2Database database = new H2Database();
      DaoFactory daoFactory = database.getDaoFactory();
      UserDao userDao = daoFactory.getUserDao();

      User user = new User("Max1", "max1", RoleType.OWNER);
      userDao.insert(user, true);

      connection.commit();

      PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
      ps.setString(1, "Max1");
      try (ResultSet result = ps.executeQuery()) {
        while (result.next()) {
          System.out.println("Inserted user: ID = " + result.getInt("id") + ", Username = " + result.getString("username"));
        }
      }

      // Retrieve the user by ID using the get method
      userDao.findAll().forEach(System.out::println);
      User retrievedUser = userDao.get(user.getUserId());
      if (retrievedUser != null) {
        System.out.println("Retrieved user: ID = " + retrievedUser.getUserId() + ", Username = " + retrievedUser.getUsername());
      } else {
        System.out.println("User not found.");
      }

      System.out.println("Hello");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
