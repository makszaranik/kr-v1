package kpi.fict.coursework.op.zaranik.services.dao.impl;

import java.util.Collection;
import java.util.List;
import kpi.fict.coursework.op.zaranik.dao.impl.QueueDaoImpl;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class QueueDaoServiceImpl implements QueueDaoService {

  private final QueueDaoImpl queueDaoImpl;

  @Override
  public Queue findQueueByName(String name) {
    return queueDaoImpl.findAll().stream()
        .filter(queue -> queue.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void addQueueToUser(User user, Queue queue) {
    if (user != null && queue != null) {
      queue.setCreator(user);
      queueDaoImpl.insert(queue);
    }
  }

  @Override
  public Collection<Queue> getUserQueues(String username) {
    return queueDaoImpl.findAll().stream()
        .filter(queue -> queue.getCreator().getUsername().equals(username))
        .toList();
  }

  @Override
  public Collection<Queue> getAllQueues() {
    return queueDaoImpl.findAll();
  }

  @Override
  @SneakyThrows
  public int getUserPosition(Queue queue, User user) {
    Queue selectedQueue = queueDaoImpl.findAll().stream()
        .filter(q -> q.getName().equals(queue.getName()))
        .findFirst()
        .orElse(null);
    if (selectedQueue == null) {
      return -1;
    }
    List<String> items = queueDaoImpl.getItemsByQueueId(selectedQueue.getId());
    if (!items.contains(user.getUsername())) {
      return -1;
    }
    return items.indexOf(user.getUsername()) + 1;
  }

  @Override
  public void updateQueue(Queue queue) {
    queueDaoImpl.update(queue);
  }

  @Override
  public void deleteQueue(Queue queue) {
    queueDaoImpl.delete(queue);
  }

  @Override
  public boolean exists(Queue queue) {
    return findQueueByName(queue.getName()) != null;
  }

  @Override
  @SneakyThrows
  public void removeFirstItemFromQueue(Queue queue) {
    List<String> items = queueDaoImpl.getItemsByQueueId(queue.getId());
    if (!items.isEmpty()) {
      String firstItem = items.get(0);
      queueDaoImpl.removeItemFromQueue(queue.getId(), firstItem);
    }
  }

  @Override
  public List<String> getItemsByQueueId(int queueId) {
    return queueDaoImpl.getItemsByQueueId(queueId);
  }

  @Override
  public void addItemInQueue(Queue queue, String item) {
    int queueId = queue.getId();
    queueDaoImpl.addItemByQueueId(queueId, item);
  }

  @Override
  public void removeItemFromQueue(Queue queue, String item) {
    int queueId = queue.getId();
    queueDaoImpl.removeItemFromQueue(queueId, item);
  }

  @Override
  @SneakyThrows
  public int getQueueSize(Queue queue) {
    return queueDaoImpl.getItemsByQueueId(queue.getId()).size();
  }

  @Override
  @SneakyThrows
  public boolean contains(Queue queue, String item) {
    List<String> items = queueDaoImpl.getItemsByQueueId(queue.getId());
    return items.contains(item);
  }

  @Override
  public boolean isBlocked(String queueName) {
    Queue queue = findQueueByName(queueName);
    return queue != null && queue.isBlocked();
  }

  @Override
  public void setBlock(String queueName, boolean isBlocked) {
    Queue queue = findQueueByName(queueName);
    if (queue != null) {
      queue.setIsBlocked(isBlocked);
      queueDaoImpl.update(queue);
    }
  }
}
