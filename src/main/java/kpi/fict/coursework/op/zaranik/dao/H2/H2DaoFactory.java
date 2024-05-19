package kpi.fict.coursework.op.zaranik.dao.H2;


import kpi.fict.coursework.op.zaranik.dao.DaoFactory;
import kpi.fict.coursework.op.zaranik.dao.QueueDao;
import kpi.fict.coursework.op.zaranik.dao.UserDao;

public class H2DaoFactory implements DaoFactory {
  H2Database database;
  UserDao userDao;
  QueueDao queueDao;

  public H2DaoFactory(H2Database database) {
    this.database = database;
    this.userDao = new H2UserDao();
    this.queueDao = new H2QueueDao(userDao);
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