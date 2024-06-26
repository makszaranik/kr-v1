package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import kpi.fict.coursework.op.zaranik.services.namevalidator.NameValidatorService;
import lombok.SneakyThrows;

@WebServlet("/deleteQueue")
public class DeleteQueueServlet extends HttpServlet {

  private QueueDaoService queueDaoService;
  private NameValidatorService nameValidatorService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    this.queueDaoService = ServiceFactory.getQueueDaoService();
    this.nameValidatorService = ServiceFactory.getNameValidatorService();
  }

  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) session.getAttribute("user");
      if (user != null) {
        request.setAttribute("queues", queueDaoService.getAllQueues());
      }
    }
    request.getRequestDispatcher("/CreateQueue.jsp").forward(request, response);
  }

  @SneakyThrows
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");


    if(!nameValidatorService.isValidName(selectedQueueName)){
      request.setAttribute("errorMessage", "Empty Form Submitted");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);
      if (selectedQueue != null) {
        queueDaoService.deleteQueue(selectedQueue);
        request.getRequestDispatcher("MainPage.jsp").forward(request, response);
      }
    } else {
      response.sendRedirect("/LoginPage.jsp");
    }
  }
}
