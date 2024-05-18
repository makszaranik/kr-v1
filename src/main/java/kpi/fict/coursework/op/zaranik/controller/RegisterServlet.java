package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kpi.fict.coursework.op.zaranik.model.Role.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.UserDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import lombok.SneakyThrows;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
  private UserDaoService userDaoService;

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    this.userDaoService = ServiceFactory.getUserDaoService();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirm_password");


    if (!password.equals(confirmPassword)) {
      response.sendRedirect("PasswordMismatch.jsp");
      return;
    }

    if (userDaoService.exists(username)) {
      response.sendRedirect("UserExists.jsp");
      return;
    }


    User newUser = new User(username, password, RoleType.USER);
    userDaoService.createUser(newUser);

    response.sendRedirect("LoginPage.jsp");
  }
}
