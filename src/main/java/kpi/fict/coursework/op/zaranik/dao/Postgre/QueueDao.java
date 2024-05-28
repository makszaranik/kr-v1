package kpi.fict.coursework.op.zaranik.dao.Postgre;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kpi.fict.coursework.op.zaranik.dao.UserDao;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;

public class QueueDao extends Dao<Queue> implements kpi.fict.coursework.op.zaranik.dao.QueueDao {

  private UserDao userDao;

  public QueueDao(UserDao userDao) {
    super();
    this.userDao = userDao;
  }

  @Override
  protected Queue mapResultSetToEntity(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    int creatorId = rs.getInt("creatorId");
    boolean isBlocked = rs.getBoolean("isBlocked");
    User creator = userDao.get(creatorId);
    Queue queue = new Queue(name, creator);
    queue.setId(id);
    queue.setBlock(isBlocked);
    return queue;
  }

  @Override
  public List<String> getItemsByQueueId(int queueId) {
    List<String> items = new ArrayList<>();
    String query = "SELECT item FROM queueItems WHERE queueId = ?";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      ps.setInt(1, queueId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          items.add(rs.getString("item"));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return items;
  }

  @Override
  public void addItemByQueueId(int queueId, String item) {
    String query = "INSERT INTO queueItems (queueId, item) VALUES (?, ?)";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      ps.setInt(1, queueId);
      ps.setString(2, item);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void removeItemFromQueue(int queueId, String item) {
    String query = "DELETE FROM queueItems WHERE id IN (" +
        "SELECT id FROM queueItems WHERE queueId = ? AND item = ? LIMIT 1)";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      ps.setInt(1, queueId);
      ps.setString(2, item);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected String getTableName() {
    return "queues";
  }

  @Override
  protected String getInsertQuery() {
    return "INSERT INTO queues (name, creatorId, isBlocked) VALUES (?, ?, ?)";
  }

  @Override
  protected void setInsertParameters(PreparedStatement ps, Queue entity) throws SQLException {
    ps.setString(1, entity.getName());
    ps.setInt(2, entity.getCreator().getId());
    ps.setBoolean(3, entity.isBlocked());
  }

  @Override
  protected String getUpdateQuery() {
    return "UPDATE queues SET name = ?, creatorId = ?, isBlocked = ? WHERE id = ?";
  }

  @Override
  protected void setUpdateParameters(PreparedStatement ps, Queue entity) throws SQLException {
    ps.setString(1, entity.getName());
    ps.setInt(2, entity.getCreator().getId());
    ps.setBoolean(3, entity.isBlocked());
    ps.setInt(4, entity.getId());
  }

  @Override
  protected Integer getId(Queue entity) {
    return entity.getId();
  }

  @Override
  protected void setGeneratedId(Queue entity, int id) {
    entity.setId(id);
  }

  @Override
  public Collection<Queue> findAll() {
    return super.findAll();
  }

  @Override
  public void insert(Queue queue) {
    super.insert(queue);
  }

  @Override
  public void delete(Queue queue) {
    super.delete(queue);
  }

  @Override
  public void update(Queue queue) {
    super.update(queue);
  }

  @Override
  public Queue get(Integer id) {
    return super.get(id);
  }
}
