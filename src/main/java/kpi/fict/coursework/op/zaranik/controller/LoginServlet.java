package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.UserDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.PasswordHashingService;
import lombok.SneakyThrows;

public class LoginServlet extends HttpServlet {

  private UserDaoService userDaoService;
  private PasswordHashingService passwordHashingService;

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    this.userDaoService = ServiceFactory.getUserDaoService();
    this.passwordHashingService = ServiceFactory.getPasswordHashingService();
  }

  @Override
  @SneakyThrows
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
  }


  @SneakyThrows
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    Optional<User> userOpt = userDaoService.findUserByUsername(username);
    if (userOpt.isEmpty()) {
      request.getRequestDispatcher("InvalidLoginOrPassword.jsp").forward(request, response);
      return;
    }

    User user = userOpt.get();
    if (passwordHashingService.checkPassword(password, user.getPassword())) {
      request.getSession().setAttribute("username", username);
      request.getSession().setAttribute("user", user);
      response.sendRedirect("MainPage.jsp");
    } else {
      request.getRequestDispatcher("InvalidLoginOrPassword.jsp").forward(request, response);
    }
  }
}
