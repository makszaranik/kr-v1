package kpi.fict.coursework.op.zaranik.dao.Postgres;


public class Database {
  public kpi.fict.coursework.op.zaranik.dao.DaoFactory getDaoFactory() {
    return new DaoFactory(this);
  }
}
