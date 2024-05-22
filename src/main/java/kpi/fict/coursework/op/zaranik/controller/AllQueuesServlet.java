package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import kpi.fict.coursework.op.zaranik.model.Queue;
import kpi.fict.coursework.op.zaranik.services.dao.QueueDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import lombok.SneakyThrows;

@WebServlet("/allQueues")
public class AllQueuesServlet extends HttpServlet {

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
    Collection<Queue> allQueues = queueDaoService.getAllQueues();
    request.setAttribute("queues", allQueues);
    request.setAttribute("tableName", "All Queues");

    if(queueDaoService.getAllQueues().isEmpty()){
      request.setAttribute("errorMessage", "No queues available");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      return;
    }

    request.getRequestDispatcher("QueueMenu.jsp").forward(request, response);
  }
}
