package kpi.fict.coursework.op.zaranik.dao.impl;

public class DaoFactory implements kpi.fict.coursework.op.zaranik.dao.DaoFactory {
  Database database;
  UserDao userDao;
  QueueDao queueDao;

  public DaoFactory(Database database) {
    this.database = database;
    this.userDao = new UserDao();
    this.queueDao = new QueueDao(userDao);
  }

  @Override
  public UserDao getUserDao() {
    return this.userDao;
  }

  @Override
  public QueueDao getQueueDao() {
    return this.queueDao;
  }

}