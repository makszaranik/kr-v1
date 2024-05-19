package kpi.fict.coursework.op.zaranik.dao;

import java.util.Collection;

public interface Dao<T> {
    T get(Integer id);
    Collection<T> findAll();
    void insert(T entitie);
    void delete(T entitie);
    void update(T entitie);
}
