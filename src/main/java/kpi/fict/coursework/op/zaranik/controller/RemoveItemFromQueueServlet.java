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

@WebServlet("/removeItemFromQueue")
public class RemoveItemFromQueueServlet extends HttpServlet {

  private QueueDaoService queueDaoService;
  private NameValidatorService nameValidatorService;

  @Override
  @SneakyThrows
  public void init(){
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
    request.getRequestDispatcher("/UpdateSelectedQueue.jsp").forward(request, response);
  }

  @Override
  @SneakyThrows
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String selectedQueueName = request.getParameter("selectedQueue");
    String itemToRemove = request.getParameter("itemToRemove");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if(!nameValidatorService.isValidName(selectedQueueName)
        || !nameValidatorService.isValidName(itemToRemove)){
      request.setAttribute("errorMessage", "Empty form submitted");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

      if(selectedQueue != null){
        if(selectedQueue.isBlocked()){
          request.setAttribute("errorMessage", "Queue is blocked");
          request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
          return;
        }
        if(selectedQueue.getQueueSize() == 0){
          request.setAttribute("errorMessage", "Queue is empty");
          request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
          return;
        }
        selectedQueue.removeItem(itemToRemove.trim());
        request.getRequestDispatcher("/MainPage.jsp").forward(request, response);
      }
    }else{
      request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
    }
  }
}
