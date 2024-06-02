package kpi.fict.coursework.op.zaranik.dao.impl;

import kpi.fict.coursework.op.zaranik.dao.QueueDao;
import kpi.fict.coursework.op.zaranik.dao.UserDao;

public class DaoFactory implements kpi.fict.coursework.op.zaranik.dao.DaoFactory {
  Database database;
  UserDao userDao;
  QueueDao queueDao;

  public DaoFactory(Database database) {
    this.database = database;
    this.userDao = new UserDaoImpl();
    this.queueDao = new QueueDaoImpl(userDao);
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