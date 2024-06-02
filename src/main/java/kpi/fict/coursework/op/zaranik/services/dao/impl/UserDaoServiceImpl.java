package kpi.fict.coursework.op.zaranik.services.dao.impl;

import java.util.Optional;
import kpi.fict.coursework.op.zaranik.dao.UserDao;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.UserDaoService;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.PasswordHashingService;

public class UserDaoServiceImpl implements UserDaoService {
  private UserDao userDao;
  private PasswordHashingService passwordHashingService;

  public UserDaoServiceImpl(UserDao userDao, PasswordHashingService passwordHashingService){
    this.userDao = userDao;
    this.passwordHashingService = passwordHashingService;
  }

  @Override
  public Optional<User> findUserByUsername(String username) {
    return userDao
        .findAll()
        .stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst();
  }

  @Override
  public void createUser(User user) {
    Optional<User> userOpt = findUserByUsername(user.getUsername());
    if(userOpt.isPresent()){
      throw new RuntimeException("user already exists");
    }
    String password = passwordHashingService.hashPassword(user.getPassword());
    user.setPassword(password);
    userDao.insert(user);
  }

  @Override
  public boolean exists(String username) {
    Optional<User> userOpt = userDao
        .findAll()
        .stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst();

    return userOpt.isPresent();
  }
}
