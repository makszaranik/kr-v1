package kpi.fict.coursework.op.zaranik.services.dao;


import kpi.fict.coursework.op.zaranik.model.User;

public interface UserDaoService {
  User findUserByUsername(String username);
  void createUser(User user);
  void deleteUser(String username);
  boolean exists(String username);
}
