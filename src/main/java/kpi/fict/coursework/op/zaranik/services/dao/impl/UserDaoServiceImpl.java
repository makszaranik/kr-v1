package kpi.fict.coursework.op.zaranik.services.dao.impl;


import kpi.fict.coursework.op.zaranik.dao.UserDao;
import kpi.fict.coursework.op.zaranik.model.*;
import kpi.fict.coursework.op.zaranik.services.dao.UserDaoService;
import lombok.SneakyThrows;

public class UserDaoServiceImpl implements UserDaoService {

  private final UserDao userDao;

  public UserDaoServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @SneakyThrows
  @Override
  public User findUserByUsername(String username) {
    return userDao.findAll().stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst().orElse(null);
  }

  @Override
  public void createUser(User user) {
    User existingUser = findUserByUsername(user.getUsername());
    if (existingUser != null) {
      throw new IllegalArgumentException("User already exists with username: " + user.getUsername());
    }
    userDao.insert(user);
  }



  @Override
  public boolean exists(String username) {
    return findUserByUsername(username) != null;
  }

}
