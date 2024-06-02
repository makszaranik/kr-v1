package kpi.fict.coursework.op.zaranik.services.dao;

import java.util.Optional;
import kpi.fict.coursework.op.zaranik.model.User;

public interface UserDaoService {

  Optional<User> findUserByUsername(String username);

  void createUser(User user);

  boolean exists(String username);
}
