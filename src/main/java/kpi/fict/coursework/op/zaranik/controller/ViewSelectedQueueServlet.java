package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import lombok.SneakyThrows;

@WebServlet("/viewSelectedQueue")
public class ViewSelectedQueueServlet extends HttpServlet {

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
    HttpSession session = request.getSession(false);
    if (session == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    User user = (User) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    String selectedQueueName = request.getParameter("selectedQueue");
    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

    if (selectedQueue == null) {
      session.setAttribute("errorMessage", "Queue not found");
      response.sendRedirect("ErrorPage.jsp");
      return;
    }

    String userPosition = String.valueOf(queueDaoService.getUserPosition(selectedQueue, user));
    if(userPosition.equals("-1")) userPosition = "you are not in queue";


    request.setAttribute("selectedQueue", selectedQueue);
    request.setAttribute("userPosition", userPosition);
    request.getRequestDispatcher("QueueContent.jsp").forward(request, response);
  }
}
