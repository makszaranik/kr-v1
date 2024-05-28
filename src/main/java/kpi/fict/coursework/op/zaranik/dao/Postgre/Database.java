package kpi.fict.coursework.op.zaranik.dao.Postgre;


public class Database {
  public DaoFactory getDaoFactory() {
    return new DaoFactory(this);
  }
}
