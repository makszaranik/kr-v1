package src.java.kpi.fict.coursework.op.zaranik.services;

import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.roleconfigurator.impl.RoleConfiguratorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class RoleConfiguratorServiceTest {

  @Mock
  private QueueDaoService queueDaoService;

  @InjectMocks
  private RoleConfiguratorServiceImpl roleConfiguratorService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetConfigurationOwner() {
    User user = new User("Max", "max123");
    Queue queue = new Queue("queue", new User("Yana", "yana123"));
    queue.setCreator(user);

    when(queueDaoService.findQueueByName("queue")).thenReturn(queue);
    RoleType result = roleConfiguratorService.getConfiguration(user, queue);

    assertThat(result).isEqualTo(RoleType.OWNER);
  }

  @Test
  void testGetConfigurationUser() {
    User creator = new User("Yana", "yana123");
    User user = new User("Max", "max123");
    Queue queue = new Queue("queue1", creator);
    queue.setCreator(creator);

    when(queueDaoService.findQueueByName("queue1")).thenReturn(queue);

    RoleType result = roleConfiguratorService.getConfiguration(user, queue);

    assertThat(result).isEqualTo(RoleType.USER);
  }

  @Test
  void testConfigureRoleOwner() {
    User user = new User("Max", "max123");
    Queue queue = new Queue("queue1", user);
    queue.setCreator(new User("User", "password"));

    when(queueDaoService.findQueueByName("queue1")).thenReturn(queue);

    roleConfiguratorService.configureRole(user, queue, RoleType.OWNER);

    assertThat(queue.getCreator()).isEqualTo(user);
  }
}
