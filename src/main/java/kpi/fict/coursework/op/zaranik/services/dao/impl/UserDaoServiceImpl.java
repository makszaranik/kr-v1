package kpi.fict.coursework.op.zaranik.services.dao.impl;

import kpi.fict.coursework.op.zaranik.dao.UserDao;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.UserDaoService;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.PasswordHashingService;

public class UserDaoServiceImpl implements UserDaoService {

  private final UserDao userDao;
  private final PasswordHashingService passwordHashingService;

  public UserDaoServiceImpl(UserDao userDao, PasswordHashingService passwordHashingService) {
    this.userDao = userDao;
    this.passwordHashingService = passwordHashingService;
  }

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
    String hashedPassword = passwordHashingService.hashPassword(user.getPassword());
    user.setPassword(hashedPassword);
    userDao.insert(user);
  }

  @Override
  public boolean exists(String username) {
    return findUserByUsername(username) != null;
  }

  public boolean validatePassword(String plainPassword, String hashedPassword) {
    return passwordHashingService.checkPassword(plainPassword, hashedPassword);
  }
}
