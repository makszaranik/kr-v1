package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import lombok.SneakyThrows;

@WebServlet("/myQueues")
public class MyQueuesServlet extends HttpServlet {

  private QueueDaoService queueDaoService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
  }

  @SneakyThrows
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();

    User user = (User) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    List<Queue> userQueues = (List<Queue>) queueDaoService.getUserQueues(user.getUsername());
    session.setAttribute("queues", userQueues);
    request.getRequestDispatcher("QueueMenu.jsp").forward(request, response);
  }
}
