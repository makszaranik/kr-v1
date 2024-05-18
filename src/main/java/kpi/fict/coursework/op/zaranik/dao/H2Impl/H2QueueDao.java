package kpi.fict.coursework.op.zaranik.dao.H2Impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kpi.fict.coursework.op.zaranik.dao.QueueDao;
import kpi.fict.coursework.op.zaranik.dao.UserDao;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;

public class H2QueueDao extends H2AbstractDao<Queue> implements QueueDao {

  private UserDao userDao;

  public H2QueueDao(UserDao userDao) {
    super();
    this.userDao = userDao;
  }

  @Override
  protected Queue mapResultSetToEntity(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    int creatorId = rs.getInt("creatorId");
    User creator = userDao.get(creatorId);
    Queue queue = new Queue(name, creator);
    queue.setId(id);
    queue.getItems().addAll(getItemsByQueueId(id));
    return queue;
  }

  private List<String> getItemsByQueueId(int queueId) throws SQLException {
    List<String> items = new ArrayList<>();
    String query = "SELECT item FROM queueItems WHERE queueId = ?";
    try (Connection connection = H2DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      ps.setInt(1, queueId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          items.add(rs.getString("item"));
        }
      }
    }
    return items;
  }

  @Override
  protected String getTableName() {
    return "queues";
  }

  @Override
  protected String getInsertQuery() {
    return "INSERT INTO queues (name, creatorId) VALUES (?, ?)";
  }

  @Override
  protected void setInsertParameters(PreparedStatement ps, Queue entity) throws SQLException {
    ps.setString(1, entity.getName());
    ps.setInt(2, entity.getCreator().getUserId());
  }

  @Override
  protected String getUpdateQuery() {
    return "UPDATE queues SET name = ?, creatorId = ? WHERE id = ?";
  }

  @Override
  protected void setUpdateParameters(PreparedStatement ps, Queue entity) throws SQLException {
    ps.setString(1, entity.getName());
    ps.setInt(2, entity.getCreator().getUserId());
    ps.setInt(3, entity.getId());
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
  public void insert(Queue queue, boolean generateId) {
    super.insert(queue, generateId);
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
