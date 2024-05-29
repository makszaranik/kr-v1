package kpi.fict.coursework.op.zaranik.dao.impl;


public class Database {
  public DaoFactory getDaoFactory() {
    return new DaoFactory(this);
  }
}
