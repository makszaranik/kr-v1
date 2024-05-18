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

@WebServlet("/blockUnblockQueue")
public class BlockUnblockQueueServlet extends HttpServlet {

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
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");


    if (!nameValidatorService.isValidName(selectedQueueName)) {
      request.setAttribute("errorMessage", "Empty Form Submitted");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

      if (selectedQueue == null) {
        request.setAttribute("errorMessage", "Selected queue not exists");
        request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        return;
      }
      selectedQueue.setBlocked(!selectedQueue.isBlocked());
      queueDaoService.updateQueue(selectedQueue);
      request.getRequestDispatcher("/MainPage.jsp").forward(request, response);
    }
  }
}
