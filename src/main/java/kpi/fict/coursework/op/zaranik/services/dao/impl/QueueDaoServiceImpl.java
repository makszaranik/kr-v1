package kpi.fict.coursework.op.zaranik.services.dao.impl;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import kpi.fict.coursework.op.zaranik.dao.QueueDao;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;

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
      queueDao.insert(queue, true);
    }
  }

  @Override
  public Collection<Queue> getUserQueues(String username) {
     List<Queue> result =  queueDao.findAll().stream()
         .filter(queue -> queue.getCreator().getUsername().equals(username))
         .collect(Collectors.toList());
     return result;
  }

  @Override
  public Collection<Queue> getAllQueues() {
      return queueDao.findAll();
  }

  @Override
  public int getUserPosition(Queue queue, User user) {
    Queue selectedQueue = queueDao.findAll().stream().
        filter(queue1 -> queue1.getName().equals(queue.getName())).
        findFirst().
        orElse(null);

    if(selectedQueue == null) return -1;
    List<String> items = selectedQueue.getItems();
    if(!items.contains(user.getUsername())) return -1;
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
  public boolean exists(Queue queue){return findQueueByName(queue.getName()) != null;}
}
