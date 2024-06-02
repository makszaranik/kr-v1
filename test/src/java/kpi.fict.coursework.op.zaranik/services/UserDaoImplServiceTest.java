package src.java.kpi.fict.coursework.op.zaranik.services;

import java.util.Optional;
import kpi.fict.coursework.op.zaranik.dao.impl.UserDaoImpl;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.impl.UserDaoServiceImpl;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.PasswordHashingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDaoImplServiceTest {

  @Mock
  private UserDaoImpl userDaoImpl;

  @Mock
  private PasswordHashingService passwordHashingService;

  @InjectMocks
  private UserDaoServiceImpl userDaoService;

  @Test
  void findUserByUsernameTest() {
    User user1 = new User("Max", "max123");
    User user2 = new User("User", "password");
    when(userDaoImpl.findAll()).thenReturn(List.of(user1, user2));

    Optional<User> optResultUser1 = userDaoService.findUserByUsername("Max");
    Optional<User> optResultUser2 = userDaoService.findUserByUsername("User");

    assertThat(optResultUser1).isPresent();
    assertThat(optResultUser2).isPresent();

    User resultUser1 = optResultUser1.get();
    User resultUser2 = optResultUser2.get();

    assertThat(resultUser1.getUsername()).isEqualTo("Max");
    assertThat(resultUser2.getUsername()).isEqualTo("User");
    verify(userDaoImpl, times(2)).findAll();
  }

  @Test
  void createUserTest() {
    User user = new User("NewUser", "newPassword");
    when(passwordHashingService.hashPassword("newPassword")).thenReturn("hashedNewPassword");

    userDaoService.createUser(user);

    verify(passwordHashingService, times(1)).hashPassword("newPassword");
    verify(userDaoImpl, times(1)).insert(user);
  }

  @Test
  void existsTest() {
    User user1 = new User("Max", "max123");

    when(userDaoImpl.findAll()).thenReturn(List.of(user1));

    boolean resultUser1 = userDaoService.exists(user1.getUsername());
    boolean resultUser2 = userDaoService.exists("User");

    assertThat(resultUser1).isTrue();
    assertThat(resultUser2).isFalse();
    verify(userDaoImpl, times(2)).findAll();
  }
}
