package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.UserDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import lombok.SneakyThrows;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private UserDaoService userDaoService;

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    this.userDaoService = ServiceFactory.getUserDaoService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
  }

  @SneakyThrows
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    User user = userDaoService.findUserByUsername(username);

    if (user == null) {
      request.getRequestDispatcher("InvalidLoginOrPassword.jsp").forward(request, response);
      return;
    }

    if (userDaoService.validatePassword(password, user.getPassword())) {
      request.getSession().setAttribute("username", username);
      request.getSession().setAttribute("user", user);
      response.sendRedirect("MainPage.jsp");
    } else {
      request.getRequestDispatcher("InvalidLoginOrPassword.jsp").forward(request, response);
    }
  }
}
