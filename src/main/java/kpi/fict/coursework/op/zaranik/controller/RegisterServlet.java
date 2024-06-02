package kpi.fict.coursework.op.zaranik.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kpi.fict.coursework.op.zaranik.model.RoleType;
import kpi.fict.coursework.op.zaranik.model.User;
import kpi.fict.coursework.op.zaranik.services.dao.UserDaoService;
import kpi.fict.coursework.op.zaranik.services.factories.ServiceFactory;
import lombok.SneakyThrows;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
  UserDaoService userDaoService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    this.userDaoService = ServiceFactory.getUserDaoService();
  }

  @Override
  @SneakyThrows
  public void doPost(HttpServletRequest request, HttpServletResponse response){
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirm_password");

    if(userDaoService.exists(username)){
      request.setAttribute("errorMessage", "user already exists");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
    }
    if(!password.equals(confirmPassword)){
      request.setAttribute("errorMessage", "Password mismatching");
      request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
    }

    User user = new User(username, password);
    userDaoService.createUser(user);

    response.sendRedirect("LoginPage.jsp");
  }

}
