package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import kpi.fict.coursework.op.zaranik.services.roleconfigurator.RoleConfiguratorService;
import lombok.SneakyThrows;

@WebServlet("/updateQueue")
public class UpdateQueueServlet extends HttpServlet {

  private QueueDaoService queueDaoService;
  private RoleConfiguratorService roleConfiguratorService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    this.queueDaoService = ServiceFactory.getQueueDaoService();
    this.roleConfiguratorService = ServiceFactory.getRoleConfiguratorService();
  }


  @Override
  @SneakyThrows
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }
    List<Queue> allQueues = new ArrayList<>(queueDaoService.getAllQueues());
    request.setAttribute("queues", allQueues);
    request.getRequestDispatcher("UpdateQueue.jsp").forward(request, response);
  }


  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String selectedAction = request.getParameter("action");
    String selectedQueueName = request.getParameter("selectedQueue");
    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");


    if(roleConfiguratorService.getConfiguration(user, selectedQueue) != RoleType.OWNER){
      System.out.println(user.getRoleType());
      request.setAttribute("errorMessage", "Permission Denied");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      return;
    }


    switch (selectedAction){
      case "add":
        request.getRequestDispatcher("/addItemInQueue").forward(request, response);
        break;
      case "removeItem":
        request.getRequestDispatcher("/removeItemFromQueue").forward(request, response);
        break;
      case "removeFirstItem":
        request.getRequestDispatcher("/removeItemFromBegin").forward(request, response);
        break;
      case "blockOrUnblockQueue":
        request.getRequestDispatcher("/blockUnblockQueue").forward(request, response);
        break;
      case "deleteQueue":
        request.getRequestDispatcher("/deleteQueue").forward(request, response);
      default:
        break;
    }
  }
}