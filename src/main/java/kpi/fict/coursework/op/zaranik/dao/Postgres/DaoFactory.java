package kpi.fict.coursework.op.zaranik.dao.Postgres;


public class DaoFactory implements kpi.fict.coursework.op.zaranik.dao.DaoFactory {
  Database database;
  kpi.fict.coursework.op.zaranik.dao.UserDao userDao;
  kpi.fict.coursework.op.zaranik.dao.QueueDao queueDao;

  public DaoFactory(Database database) {
    this.database = database;
    this.userDao = new UserDao();
    this.queueDao = new QueueDao(userDao);
  }

  @Override
  public kpi.fict.coursework.op.zaranik.dao.UserDao getUserDao() {
    return this.userDao;
  }

  @Override
  public kpi.fict.coursework.op.zaranik.dao.QueueDao getQueueDao() {
    return this.queueDao;
  }

}