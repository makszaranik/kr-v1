package kpi.fict.coursework.op.zaranik.services.roleconfigurator.impl;


import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import kpi.fict.coursework.op.zaranik.services.roleconfigurator.RoleConfiguratorService;

public class RoleConfiguratorServiceImpl implements RoleConfiguratorService {
  private QueueDaoService queueDaoService = ServiceFactory.getQueueDaoService();

  @Override
  public RoleType getConfiguration(User user, Queue queue) {
    if(queueDaoService.findQueueByName(queue.getName()).getCreator().equals(user)){
      return RoleType.OWNER;
    }
    return RoleType.USER;
  }

  @Override
  public void configureRole(User user, Queue queue, RoleType roleType) {
    if(roleType == RoleType.OWNER){
        Queue existingQueue = queueDaoService.findQueueByName(queue.getName());
        if(existingQueue != null){
          existingQueue.setCreator(user);
        }
    }
  }
}
