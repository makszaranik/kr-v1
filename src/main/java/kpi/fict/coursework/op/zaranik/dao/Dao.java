package kpi.fict.coursework.op.zaranik.dao;

import java.util.Collection;

public interface Dao<T> {
    T get(Integer id);
    Collection<T> findAll();
    void insert(T entity);
    void delete(T entity);
    void update(T entity);
}
