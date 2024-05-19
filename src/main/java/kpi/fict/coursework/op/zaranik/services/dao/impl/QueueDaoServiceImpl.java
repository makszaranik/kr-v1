package kpi.fict.coursework.op.zaranik.services.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import kpi.fict.coursework.op.zaranik.dao.Postgres.QueueDao;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import lombok.SneakyThrows;

public class QueueDaoServiceImpl implements QueueDaoService {

  private final QueueDao queueDao;

  public QueueDaoServiceImpl(QueueDao queueDao) {
    this.queueDao = queueDao;
  }

  @Override
  public Queue findQueueByName(String name) {
    return queueDao.findAll().stream()
        .filter(queue -> queue.getName().equals(name))
        .findFirst().orElse(null);
  }

  @Override
  public void addQueueToUser(User user, Queue queue) {
    if (user != null && queue != null) {
      queue.setCreator(user);
      queueDao.insert(queue);
    }
  }

  @Override
  public Collection<Queue> getUserQueues(String username) {
    return queueDao.findAll().stream()
        .filter(queue -> queue.getCreator().getUsername().equals(username))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Queue> getAllQueues() {
    return queueDao.findAll();
  }

  @Override
  @SneakyThrows
  public int getUserPosition(Queue queue, User user) {
    Queue selectedQueue = queueDao.findAll().stream()
        .filter(q -> q.getName().equals(queue.getName()))
        .findFirst().orElse(null);
    if (selectedQueue == null) return -1;
    List<String> items = ((QueueDao) queueDao).getItemsByQueueId(selectedQueue.getId());
    if (!items.contains(user.getUsername())) return -1;
    return items.indexOf(user.getUsername()) + 1;
  }

  @Override
  public void updateQueue(Queue queue) {
    queueDao.update(queue);
  }

  @Override
  public void delete(Queue queue) {
    queueDao.delete(queue);
  }

  @Override
  public boolean exists(Queue queue) {
    return findQueueByName(queue.getName()) != null;
  }

  @Override
  @SneakyThrows
  public void removeFirstItemFromQueue(Queue queue) {
    List<String> items = ((QueueDao) queueDao).getItemsByQueueId(queue.getId());
    if (!items.isEmpty()) {
      String firstItem = items.get(0);
      queueDao.removeItemFromQueue(queue.getId(), firstItem);
    }
  }

  @Override
  public List<String> getItemsByQueueId(int queueId) {
    return ((QueueDao) queueDao).getItemsByQueueId(queueId);
  }

  @Override
  public void addItemInQueue(Queue queue, String item) {
    int queueId = queue.getId();
    queueDao.addItemByQueueId(queueId, item);
  }

  @Override
  public void removeItemFromQueue(Queue queue, String item) {
    int queueId = queue.getId();
    queueDao.removeItemFromQueue(queueId, item);
  }

  @Override
  @SneakyThrows
  public int getQueueSize(Queue queue) {
    return ((QueueDao) queueDao).getItemsByQueueId(queue.getId()).size();
  }

  @Override
  @SneakyThrows
  public boolean contains(Queue queue, String item) {
    List<String> items = ((QueueDao) queueDao).getItemsByQueueId(queue.getId());
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
      queue.setBlock(isBlocked);
      queueDao.update(queue);
    }
  }
}
