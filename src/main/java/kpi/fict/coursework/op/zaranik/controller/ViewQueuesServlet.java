package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import kpi.fict.coursework.op.zaranik.model.User;

@WebServlet("/viewQueues")
public class ViewQueuesServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    User user = (User) session.getAttribute("user");


    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    request.getRequestDispatcher("/Queues.jsp").forward(request, response);
  }
}
