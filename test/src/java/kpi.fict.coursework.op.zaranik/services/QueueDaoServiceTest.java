package src.java.kpi.fict.coursework.op.zaranik.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import kpi.fict.coursework.op.zaranik.dao.Postgres.QueueDao;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.impl.QueueDaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    assertNotNull(result);
    assertEquals("TestQueue", result.getName());
    verify(queueDao, times(1)).findAll();
  }

  @Test
  void addQueueToUserTest() {
    User user = new User("User", "password");
    Queue queue = new Queue("TestQueue", user);

    queueDaoService.addQueueToUser(user, queue);

    verify(queueDao, times(1)).insert(queue);
    assertEquals(user, queue.getCreator());
  }

  @Test
  void getUserQueuesTest() {
    User user = new User("User", "password");
    Queue queue1 = new Queue("TestQueue", user);
    Queue queue2 = new Queue("MyQueue", user);
    Queue queue3 = new Queue("TestQueue", new User("Max", "max123"));

    when(queueDao.findAll()).thenReturn(List.of(queue1, queue2, queue3));

    List<Queue> result = (List<Queue>) queueDaoService.getUserQueues("User");

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(user, result.get(0).getCreator());
    verify(queueDao, times(1)).findAll();
  }

  @Test
  void getUserPositionTest(){
    User user = new User("User", "password");

    Queue queue = new Queue("TestQueue", user);
    queue.setId(1);

    when(queueDao.findAll()).thenReturn(List.of(queue));
    when(queueDao.getItemsByQueueId(1)).thenReturn(Arrays.asList("User1", "User", "User3"));

    int position = queueDaoService.getUserPosition(queue, user);

    assertEquals(2, position);
    verify(queueDao, times(1)).findAll();
    verify(queueDao, times(1)).getItemsByQueueId(1);
  }

  @Test
  void removeFirstItemFromQueueTest(){
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

    assertEquals(2, size);
    verify(queueDao, times(1)).getItemsByQueueId(1);
  }

  @Test
  void containsTest(){
    Queue queue = new Queue("TestQueue", new User("User", "password"));
    queue.setId(1);

    when(queueDao.getItemsByQueueId(1)).thenReturn(Arrays.asList("item1", "item2"));

    boolean containsItem1 = queueDaoService.contains(queue, "item1");
    boolean containsItem2 = queueDaoService.contains(queue, "item2");
    boolean containsItem3 = queueDaoService.contains(queue, "item3");

    assertTrue(containsItem1);
    assertTrue(containsItem2);
    assertFalse(containsItem3);
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
    assertTrue(isBlocked);
    verify(queueDao, times(2)).findAll();
    verify(queueDao, times(1)).update(queue);
  }

  @Test
  void setBlockTest() {
    Queue queue = new Queue("TestQueue", new User("TestUser", "password"));

    when(queueDao.findAll()).thenReturn(List.of(queue));

    queueDaoService.setBlock("TestQueue", true);

    verify(queueDao, times(1)).update(queue);
    assertTrue(queue.isBlocked());
  }
}
