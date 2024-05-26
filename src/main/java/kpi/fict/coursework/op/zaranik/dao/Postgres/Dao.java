package kpi.fict.coursework.op.zaranik.dao.Postgres;


import java.sql.*;
import java.util.*;

public abstract class Dao<T> implements kpi.fict.coursework.op.zaranik.dao.Dao<T> {

  protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

  protected abstract String getTableName();

  protected abstract String getInsertQuery();

  protected abstract void setInsertParameters(PreparedStatement ps, T entity) throws SQLException;

  protected abstract String getUpdateQuery();

  protected abstract void setUpdateParameters(PreparedStatement ps, T entity) throws SQLException;

  protected abstract Integer getId(T entity);

  protected abstract void setGeneratedId(T entity, int id);


  @Override
  public T get(Integer id) {
    String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return mapResultSetToEntity(rs);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  @Override
  public Collection<T> findAll() {
    List<T> entities = new ArrayList<>();
    String query = "SELECT * FROM " + getTableName();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        entities.add(mapResultSetToEntity(rs));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return entities;
  }


  @Override
  public void insert(T entity) {
    String insertSql = getInsertQuery();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
      setInsertParameters(ps, entity);
      ps.executeUpdate();
      try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          setGeneratedId(entity, generatedKeys.getInt(1));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(T entity) {
    String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      ps.setInt(1, getId(entity));
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(T entity) {
    String query = getUpdateQuery();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
      setUpdateParameters(ps, entity);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
