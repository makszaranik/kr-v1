package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import kpi.fict.coursework.op.zaranik.services.namevalidator.NameValidatorService;
import kpi.fict.coursework.op.zaranik.services.roleconfigurator.RoleConfiguratorService;
import lombok.SneakyThrows;

@WebServlet("/createQueue")
public class CreateQueueServlet extends HttpServlet {

  private QueueDaoService queueDaoService;
  private RoleConfiguratorService roleConfiguratorService;
  private NameValidatorService nameValidatorService;

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
    roleConfiguratorService = ServiceFactory.getRoleConfiguratorService();
    nameValidatorService = ServiceFactory.getNameValidatorService();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }
    request.getRequestDispatcher("/CreateQueue.jsp").forward(request, response);
  }

  @SneakyThrows
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    String selectedQueueName = request.getParameter("queueName");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    if (!nameValidatorService.isValidName(selectedQueueName)) {
      request.setAttribute("errorMessage", "Empty Form Submitted");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      return;
    }

    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);
    if (selectedQueue != null && queueDaoService.exists(selectedQueue)) {
      request.setAttribute("errorMessage", "Queue Is Already Created");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      return;
    }

    Queue queue = new Queue(selectedQueueName, user);
    roleConfiguratorService.configureRole(user, queue, RoleType.OWNER);
    queueDaoService.addQueueToUser(user, queue);
    queueDaoService.updateQueue(queue);
    response.sendRedirect("/MainPage.jsp");
  }

}
