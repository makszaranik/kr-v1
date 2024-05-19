package kpi.fict.coursework.op.zaranik.services.dao;

import java.util.Collection;
import java.util.List;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;

public interface QueueDaoService {
  Queue findQueueByName(String name);
  void addQueueToUser(User user, Queue queue);
  Collection<Queue> getUserQueues(String username);
  Collection<Queue> getAllQueues();
  int getUserPosition(Queue queue, User user);
  void updateQueue(Queue queue);
  void delete(Queue queue);
  boolean exists(Queue queue);
  void removeFirstItemFromQueue(Queue queue);
  List<String> getItemsByQueueId(int queueId);
  int getQueueSize(Queue queue);
  boolean contains(Queue queue, String item);
  void addItemInQueue(Queue queue, String item);
  void removeItemFromQueue(Queue queue, String item);
  boolean isBlocked(String queueName);
  void setBlock(String queueName, boolean isBlocked);
}
