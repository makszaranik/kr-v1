package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

public class LogoutServlet extends HttpServlet {

  @Override
  @SneakyThrows
  protected void doGet(HttpServletRequest request, HttpServletResponse response){
    HttpSession session = request.getSession();
    session.invalidate();
    response.sendRedirect("/LoginPage.jsp");
  }
}
