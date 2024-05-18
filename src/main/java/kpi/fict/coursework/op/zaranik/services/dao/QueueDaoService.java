package kpi.fict.coursework.op.zaranik.services.dao;


import java.util.Collection;
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
}
