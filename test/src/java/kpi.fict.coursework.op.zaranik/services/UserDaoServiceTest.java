package src.java.kpi.fict.coursework.op.zaranik.services;

import kpi.fict.coursework.op.zaranik.dao.Postgres.UserDao;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.impl.UserDaoServiceImpl;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.PasswordHashingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserDaoServiceTest {

  @Mock
  private UserDao userDao;

  @Mock
  private PasswordHashingService passwordHashingService;

  @InjectMocks
  private UserDaoServiceImpl userDaoService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findUserByUsernameTest() {
    User user1 = new User("Max", "max123");
    User user2 = new User("User", "password");
    when(userDao.findAll()).thenReturn(List.of(user1, user2));

    User resultUser1 = userDaoService.findUserByUsername("Max");
    User resultUser2 = userDaoService.findUserByUsername("User");

    assertThat(resultUser1).isNotNull();
    assertThat(resultUser2).isNotNull();

    assertThat(resultUser1.getUsername()).isEqualTo("Max");
    assertThat(resultUser2.getUsername()).isEqualTo("User");
    verify(userDao, times(2)).findAll();
  }

  @Test
  void createUserTest() {
    User user = new User("NewUser", "newPassword");
    when(passwordHashingService.hashPassword("newPassword")).thenReturn("hashedNewPassword");

    userDaoService.createUser(user);

    verify(passwordHashingService, times(1)).hashPassword("newPassword");
    verify(userDao, times(1)).insert(user);
  }

  @Test
  void existsTest() {
    User user1 = new User("Max", "max123");

    when(userDao.findAll()).thenReturn(List.of(user1));

    boolean resultUser1 = userDaoService.exists(user1.getUsername());
    boolean resultUser2 = userDaoService.exists("User");

    assertThat(resultUser1).isTrue();
    assertThat(resultUser2).isFalse();
    verify(userDao, times(2)).findAll();
  }
}
