package kpi.fict.coursework.op.zaranik;

import jakarta.servlet.Servlet;
import kpi.fict.coursework.op.zaranik.controller.AddItemInQueueServlet;
import kpi.fict.coursework.op.zaranik.controller.AddMeInQueueServlet;
import kpi.fict.coursework.op.zaranik.controller.AllQueuesServlet;
import kpi.fict.coursework.op.zaranik.controller.BlockUnblockQueueServlet;
import kpi.fict.coursework.op.zaranik.controller.CreateQueueServlet;
import kpi.fict.coursework.op.zaranik.controller.DeleteQueueServlet;
import kpi.fict.coursework.op.zaranik.controller.LoginServlet;
import kpi.fict.coursework.op.zaranik.controller.LogoutServlet;
import kpi.fict.coursework.op.zaranik.controller.MyQueuesServlet;
import kpi.fict.coursework.op.zaranik.controller.RegisterServlet;
import kpi.fict.coursework.op.zaranik.controller.RemoveItemFromBeginServlet;
import kpi.fict.coursework.op.zaranik.controller.RemoveItemFromQueueServlet;
import kpi.fict.coursework.op.zaranik.controller.UpdateQueueServlet;
import kpi.fict.coursework.op.zaranik.controller.ViewQueuesServlet;
import kpi.fict.coursework.op.zaranik.controller.ViewSelectedQueueServlet;
import kpi.fict.coursework.op.zaranik.web.ApplicationContextListener;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbeddedTomcatServer {

  public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setBaseDir("temp");
    tomcat.setPort(8080);
    tomcat.getConnector();
    String contextPath = "";
    String docBase = new File("src/main/webapp").getAbsolutePath();
    Context context = tomcat.addWebapp(contextPath, docBase);
    context.addWelcomeFile("LoginPage.jsp");
    context.addApplicationListener(ApplicationContextListener.class.getName());


    addServlet(tomcat, context, contextPath, "LoginServlet", "/login", new LoginServlet());
    addServlet(tomcat, context, contextPath, "RegisterServlet", "/register", new RegisterServlet());
    addServlet(tomcat, context, contextPath, "LogoutServlet", "/logout", new LogoutServlet());
    addServlet(tomcat, context, contextPath, "CreateQueueServlet", "/createQueue", new CreateQueueServlet());
    addServlet(tomcat, context, contextPath, "ViewQueuesServlet", "/viewQueues", new ViewQueuesServlet());
    addServlet(tomcat, context, contextPath, "MyQueuesServlet", "/myQueues", new MyQueuesServlet());
    addServlet(tomcat, context, contextPath, "AllQueuesServlet", "/allQueues", new AllQueuesServlet());
    addServlet(tomcat, context, contextPath, "ViewSelectedQueueServlet", "/viewSelectedQueue", new ViewSelectedQueueServlet());
    addServlet(tomcat, context, contextPath, "DeleteQueueServlet", "/deleteQueue", new DeleteQueueServlet());
    addServlet(tomcat, context, contextPath, "BlockUnblockQueueServlet", "/blockUnblockQueue", new BlockUnblockQueueServlet());
    addServlet(tomcat, context, contextPath, "UpdateQueueServlet", "/updateQueue", new UpdateQueueServlet());
    addServlet(tomcat, context, contextPath, "AddItemInQueueServlet", "/addItemInQueue", new AddItemInQueueServlet());
    addServlet(tomcat, context, contextPath, "RemoveItemFromQueueServlet", "/removeItemFromQueue", new RemoveItemFromQueueServlet());
    addServlet(tomcat, context, contextPath, "RemoveItemFromBeginServlet", "/removeItemFromBegin", new RemoveItemFromBeginServlet());
    addServlet(tomcat, context, contextPath, "AddMeInQueueServlet", "/addMeInQueue", new AddMeInQueueServlet());



    tomcat.start();
    tomcat.getServer().await();
  }

  private static void addServlet(Tomcat tomcat, Context context, String contextPath, String servletName, String urlPattern, Servlet servlet) {
    tomcat.addServlet(contextPath, servletName, servlet);
    context.addServletMappingDecoded(urlPattern, servletName);
  }
}
