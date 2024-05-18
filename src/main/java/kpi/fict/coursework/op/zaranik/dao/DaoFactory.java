package kpi.fict.coursework.op.zaranik.dao;

public interface DaoFactory {
  QueueDao getQueueDao();
  UserDao getUserDao();
}
