package kpi.fict.coursework.op.zaranik.dao.H2Impl;


import kpi.fict.coursework.op.zaranik.dao.DaoFactory;

public class H2Database {
  public DaoFactory getDaoFactory() {
    return new H2DaoFactory(this);
  }
}
