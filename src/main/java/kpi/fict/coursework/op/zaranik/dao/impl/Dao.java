package kpi.fict.coursework.op.zaranik.dao.impl;


import java.sql.*;
import java.util.*;
import lombok.SneakyThrows;

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
  @SneakyThrows
  public T get(Integer id) {
    String sqlQuery = "SELECT * FROM " + getTableName() + " WHERE id = ?";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return mapResultSetToEntity(rs);
        }
      }
    }
    return null;
  }

  @Override
  @SneakyThrows
  public Collection<T> findAll() {
    List<T> entities = new ArrayList<>();
    String sqlQuery = "SELECT * FROM " + getTableName();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        entities.add(mapResultSetToEntity(rs));
      }
    }
    return entities;
  }


  @Override
  @SneakyThrows
  public void insert(T entity) {
    String sqlQuery = getInsertQuery();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
      setInsertParameters(ps, entity);
      ps.executeUpdate();
      try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          setGeneratedId(entity, generatedKeys.getInt(1));
        }
      }
    }
  }

  @Override
  @SneakyThrows
  public void delete(T entity) {
    String sqlQuery = "DELETE FROM " + getTableName() + " WHERE id = ?";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
      ps.setInt(1, getId(entity));
      ps.executeUpdate();
    }
  }

  @Override
  @SneakyThrows
  public void update(T entity) {
    String sqlQuery = getUpdateQuery();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
      setUpdateParameters(ps, entity);
      ps.executeUpdate();
    }
  }
}
