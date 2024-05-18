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

@WebServlet("/removeItemFromBegin")
public class RemoveItemFromBeginServlet extends HttpServlet {

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
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");


    if (user != null) {
      String selectedQueueName = request.getParameter("selectedQueue");

      if(!nameValidatorService.isValidName(selectedQueueName)){
        request.setAttribute("errorMessage", "Empty form submitted");
        request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        return;
      }

      Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

      if (selectedQueue != null) {
        if (selectedQueue.isBlocked()) {
          request.setAttribute("errorMessage", "Queue is blocked");
          request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
          return;
        }
        if(queueDaoService.getQueueSize(selectedQueue) == 0){
          request.setAttribute("errorMessage", "Queue is empty");
          request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
          return;
        }
        queueDaoService.removeFirstItemFromQueue(selectedQueue);
        request.getRequestDispatcher("/MainPage.jsp").forward(request, response);
      }
    }else{
      request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
    }
  }
}

