package kpi.fict.coursework.op.zaranik.services.roleconfigurator;


import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;

public interface RoleConfiguratorService {
  RoleType getConfiguration(User user, Queue queue);
  void configureRole(User user, Queue queue, RoleType roleType);
}
