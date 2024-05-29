package kpi.fict.coursework.op.zaranik.services.factories;

import jakarta.servlet.ServletContext;
import kpi.fict.coursework.op.zaranik.dao.DaoFactory;
import kpi.fict.coursework.op.zaranik.dao.impl.QueueDao;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.dao.UserDaoService;
import kpi.fict.coursework.op.zaranik.services.dao.impl.QueueDaoServiceImpl;
import kpi.fict.coursework.op.zaranik.services.dao.impl.UserDaoServiceImpl;
import kpi.fict.coursework.op.zaranik.services.namevalidator.NameValidatorService;
import kpi.fict.coursework.op.zaranik.services.namevalidator.impl.NameValidatorServiceImpl;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.PasswordHashingService;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.impl.PasswordHashingServiceImpl;
import kpi.fict.coursework.op.zaranik.services.roleconfigurator.RoleConfiguratorService;
import kpi.fict.coursework.op.zaranik.services.roleconfigurator.impl.RoleConfiguratorServiceImpl;
import lombok.Getter;

public class ServiceFactory {
  @Getter
  private static UserDaoService userDaoService;
  @Getter
  private static QueueDaoService queueDaoService;
  @Getter
  private static RoleConfiguratorService roleConfiguratorService;
  @Getter
  private static NameValidatorService nameValidatorService;
  @Getter
  private static PasswordHashingService passwordHashingService;

  public static void init(ServletContext context) {
    DaoFactory daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    passwordHashingService = new PasswordHashingServiceImpl();
    userDaoService = new UserDaoServiceImpl(daoFactory.getUserDao(), passwordHashingService);
    queueDaoService = new QueueDaoServiceImpl((QueueDao) daoFactory.getQueueDao());
    roleConfiguratorService = new RoleConfiguratorServiceImpl();
    nameValidatorService = new NameValidatorServiceImpl();
  }
}
