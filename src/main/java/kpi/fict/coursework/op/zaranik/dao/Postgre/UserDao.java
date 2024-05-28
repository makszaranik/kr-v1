package kpi.fict.coursework.op.zaranik.dao.Postgre;


import java.sql.*;
import java.util.Collection;
import kpi.fict.coursework.op.zaranik.model.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;

public class UserDao extends Dao<User> implements kpi.fict.coursework.op.zaranik.dao.UserDao {

  public UserDao() {
    super();
  }

  @Override
  protected User mapResultSetToEntity(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String username = rs.getString("username");
    String password = rs.getString("password");
    RoleType roleType = RoleType.valueOf(rs.getString("roleType"));
    return new User(id, username, password, roleType);
  }

  @Override
  protected String getTableName() {
    return "users";
  }

  @Override
  protected String getInsertQuery() {
    return "INSERT INTO users (username, password, roleType) VALUES (?, ?, ?)";
  }

  @Override
  protected void setInsertParameters(PreparedStatement ps, User entity) throws SQLException {
    ps.setString(1, entity.getUsername());
    ps.setString(2, entity.getPassword());
    ps.setString(3, entity.getRoleType().name());
  }

  @Override
  protected String getUpdateQuery() {
    return "UPDATE users SET username = ?, password = ?, roleType = ? WHERE id = ?";
  }

  @Override
  protected void setUpdateParameters(PreparedStatement ps, User entity) throws SQLException {
    ps.setString(1, entity.getUsername());
    ps.setString(2, entity.getPassword());
    ps.setString(3, entity.getRoleType().name());
    ps.setInt(4, entity.getUserId());
  }

  @Override
  protected Integer getId(User entity) {
    return entity.getUserId();
  }

  @Override
  protected void setGeneratedId(User entity, int id) {
    entity.setUserId(id);
  }

  @Override
  public Collection<User> findAll() {
    return super.findAll();
  }

  @Override
  public void insert(User user) {
    super.insert(user);
  }

  @Override
  public void delete(User user) {
    super.delete(user);
  }

  @Override
  public void update(User user) {
    super.update(user);
  }

  @Override
  public User get(Integer id) {
    return super.get(id);
  }
}
