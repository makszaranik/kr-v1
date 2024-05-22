package kpi.fict.coursework.op.zaranik.dao.Postgres;


public class Database {
  public DaoFactory getDaoFactory() {
    return new DaoFactory(this);
  }
}
