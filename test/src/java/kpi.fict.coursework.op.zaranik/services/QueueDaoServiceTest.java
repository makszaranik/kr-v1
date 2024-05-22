package src.java.kpi.fict.coursework.op.zaranik.services;

import kpi.fict.coursework.op.zaranik.dao.Postgres.QueueDao;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.impl.QueueDaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class QueueDaoServiceTest {

  @Mock
  private QueueDao queueDao;

  @InjectMocks
  private QueueDaoServiceImpl queueDaoService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findQueueByNameTest() {
    Queue queue1 = new Queue("TestQueue", new User("Max", "max123"));
    Queue queue2 = new Queue("MyQueue", new User("Lisa", "lisa123"));

    when(queueDao.findAll()).thenReturn(List.of(queue1, queue2));

    Queue result = queueDaoService.findQueueByName("TestQueue");

    assertThat(result).isNotNull();
    assertThat(result.getName()).isEqualTo("TestQueue");
    verify(queueDao, times(1)).findAll();
  }

  @Test
  void addQueueToUserTest() {
    User user = new User("User", "password");
    Queue queue = new Queue("TestQueue", user);

    queueDaoService.addQueueToUser(user, queue);

    verify(queueDao, times(1)).insert(queue);
    assertThat(queue.getCreator()).isEqualTo(user);
  }

  @Test
  void getUserQueuesTest() {
    User user = new User("User", "password");
    Queue queue1 = new Queue("TestQueue", user);
    Queue queue2 = new Queue("MyQueue", user);
    Queue queue3 = new Queue("TestQueue", new User("Max", "max123"));

    when(queueDao.findAll()).thenReturn(List.of(queue1, queue2, queue3));

    List<Queue> result = (List<Queue>) queueDaoService.getUserQueues("User");

    assertThat(result).isNotNull();
    assertThat(result).hasSize(2);
    assertThat(result).allMatch(queue -> queue.getCreator().equals(user));
    verify(queueDao, times(1)).findAll();
  }

  @Test
  void getUserPositionTest() {
    User user = new User("User", "password");

    Queue queue = new Queue("TestQueue", user);
    queue.setId(1);

    when(queueDao.findAll()).thenReturn(List.of(queue));
    when(queueDao.getItemsByQueueId(1)).thenReturn(Arrays.asList("User1", "User", "User3"));

    int position = queueDaoService.getUserPosition(queue, user);

    assertThat(position).isEqualTo(2);
    verify(queueDao, times(1)).findAll();
    verify(queueDao, times(1)).getItemsByQueueId(1);
  }

  @Test
  void removeFirstItemFromQueueTest() {
    Queue queue = new Queue("TestQueue", new User("User", "password"));
    queue.setId(1);

    when(queueDao.getItemsByQueueId(1)).thenReturn(Arrays.asList("User1", "User2"));

    queueDaoService.removeFirstItemFromQueue(queue);

    verify(queueDao, times(1)).removeItemFromQueue(1, "User1");
  }

  @Test
  void getQueueSizeTest() {
    Queue queue = new Queue("TestQueue", new User("User", "password"));
    queue.setId(1);

    when(queueDao.getItemsByQueueId(1)).thenReturn(Arrays.asList("User1", "User2"));

    int size = queueDaoService.getQueueSize(queue);

    assertThat(size).isEqualTo(2);
    verify(queueDao, times(1)).getItemsByQueueId(1);
  }

  @Test
  void containsTest() {
    Queue queue = new Queue("TestQueue", new User("User", "password"));
    queue.setId(1);

    when(queueDao.getItemsByQueueId(1)).thenReturn(Arrays.asList("item1", "item2"));

    boolean containsItem1 = queueDaoService.contains(queue, "item1");
    boolean containsItem2 = queueDaoService.contains(queue, "item2");
    boolean containsItem3 = queueDaoService.contains(queue, "item3");

    assertThat(containsItem1).isTrue();
    assertThat(containsItem2).isTrue();
    assertThat(containsItem3).isFalse();
    verify(queueDao, times(3)).getItemsByQueueId(1);
  }

  @Test
  void isBlockedTest() {
    User user = new User("User", "password");
    Queue queue = new Queue("TestQueue", user);
    queue.setId(1);

    when(queueDao.findAll()).thenReturn(List.of(queue));
    queueDaoService.setBlock(queue.getName(), true);
    when(queueDao.findAll()).thenReturn(List.of(queue));

    boolean isBlocked = queueDaoService.isBlocked("TestQueue");

    assertThat(isBlocked).isTrue();
    verify(queueDao, times(2)).findAll();
    verify(queueDao, times(1)).update(queue);
  }

  @Test
  void setBlockTest() {
    Queue queue = new Queue("TestQueue", new User("TestUser", "password"));

    when(queueDao.findAll()).thenReturn(List.of(queue));

    queueDaoService.setBlock("TestQueue", true);

    verify(queueDao, times(1)).update(queue);
    assertThat(queue.isBlocked()).isTrue();
  }
}
