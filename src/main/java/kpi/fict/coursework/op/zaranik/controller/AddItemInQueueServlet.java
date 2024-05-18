package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import kpi.fict.coursework.op.zaranik.services.namevalidator.NameValidatorService;
import lombok.SneakyThrows;

@WebServlet("/addItemInQueue")
public class AddItemInQueueServlet extends HttpServlet {

  private QueueDaoService queueDaoService;
  private NameValidatorService nameValidatorService;

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    this.queueDaoService = ServiceFactory.getQueueDaoService();
    this.nameValidatorService = ServiceFactory.getNameValidatorService();
  }


  @Override
  @SneakyThrows
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) session.getAttribute("user");
      if (user != null) {
        List<Queue> userQueues = (List<Queue>) queueDaoService.getUserQueues(user.getUsername());
        request.setAttribute("queues", userQueues);
      }
    }
    request.getRequestDispatcher("/UpdateQueue.jsp").forward(request, response);
  }


  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String selectedQueueName = request.getParameter("selectedQueue");
    String newItem = request.getParameter("newItem");

    if(!nameValidatorService.isValidName(selectedQueueName)
        || !nameValidatorService.isValidName(newItem)){
      request.setAttribute("errorMessage", "Empty Form Submitted");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      return;
    }

    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

    if(selectedQueue != null){
      if(selectedQueue.isBlocked()){
        request.setAttribute("errorMessage", "Queue Is blocked");
        request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        return;
      }
      if(queueDaoService.contains(selectedQueue, newItem)){
        request.setAttribute("errorMessage", "Item is already exists");
        request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        return;
      }

      queueDaoService.addItemInQueue(selectedQueue, newItem.trim());
      request.getRequestDispatcher("/MainPage.jsp").forward(request, response);
    }
  }

}