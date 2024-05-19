package kpi.fict.coursework.op.zaranik.web;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import kpi.fict.coursework.op.zaranik.dao.DaoFactory;
import kpi.fict.coursework.op.zaranik.dao.H2.H2Database;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    H2Database database = new H2Database();
    DaoFactory daoFactory = database.getDaoFactory();
    sce.getServletContext().setAttribute("daoFactory", daoFactory);
    sce.getServletContext().setAttribute("database", database);
    ServiceFactory.init(sce.getServletContext());
    System.out.println("ApplicationContext initialized with ServiceFactory and H2 database");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {}
}
