/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.0-M11
 * Generated at: 2024-05-18 10:06:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import kpi.fict.coursework.op.zaranik.model.User;

public final class MainPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/Header.jsp", Long.valueOf(1716019252837L));
    _jspx_dependants.put("file:/C:/Users/Asus/.m2/repository/org/glassfish/web/jakarta.servlet.jsp.jstl/2.0.0/jakarta.servlet.jsp.jstl-2.0.0.jar", Long.valueOf(1716018685652L));
    _jspx_dependants.put("jar:file:/C:/Users/Asus/.m2/repository/org/glassfish/web/jakarta.servlet.jsp.jstl/2.0.0/jakarta.servlet.jsp.jstl-2.0.0.jar!/META-INF/c.tld", Long.valueOf(1602863172000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("kpi.fict.coursework.op.zaranik.model.User");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Header</title>\r\n");
      out.write("    <style>\r\n");
      out.write("      .header {\r\n");
      out.write("        background-color: #333;\r\n");
      out.write("        color: #fff;\r\n");
      out.write("        padding: 10px;\r\n");
      out.write("        text-align: right;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      .header span {\r\n");
      out.write("        margin-right: 10px;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      .header a {\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        color: #fff;\r\n");
      out.write("        padding: 5px 10px;\r\n");
      out.write("        border-radius: 5px;\r\n");
      out.write("        transition: background-color 0.3s ease;\r\n");
      out.write("        margin-right: 10px;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      .header a:hover {\r\n");
      out.write("        background-color: #555;\r\n");
      out.write("      }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");

    HttpSession httpSession = (HttpSession) request.getSession(false);
    User user = null;
    if (httpSession != null) {
        user = (User) httpSession.getAttribute("user");
    }

      out.write("\r\n");
      out.write("<div class=\"header\">\r\n");
      out.write("    <a href=\"MainPage.jsp\">Main Page</a>\r\n");
      out.write("    ");
 if (user != null) { 
      out.write("\r\n");
      out.write("    <span>Welcome, ");
      out.print( session.getAttribute("username") );
      out.write("!</span>\r\n");
      out.write("    ");
 } 
      out.write("\r\n");
      out.write("    <a href=\"logout\">Logout</a>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Main Page</title>\r\n");
      out.write("    <style>\r\n");
      out.write("      body {\r\n");
      out.write("        font-family: Arial, sans-serif;\r\n");
      out.write("        background-color: #f4f4f4;\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        padding: 0;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      .header {\r\n");
      out.write("        background-color: #333;\r\n");
      out.write("        color: #fff;\r\n");
      out.write("        padding: 10px;\r\n");
      out.write("        text-align: right;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      h2 {\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        margin-top: 50px;\r\n");
      out.write("        color: #333;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      ul {\r\n");
      out.write("        list-style-type: none;\r\n");
      out.write("        padding: 0;\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      li {\r\n");
      out.write("        display: inline;\r\n");
      out.write("        margin-right: 20px;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("      a {\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        color: #007bff;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      a:hover {\r\n");
      out.write("        text-decoration: underline;\r\n");
      out.write("      }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h2>Main Page</h2>\r\n");
      out.write("<ul>\r\n");
      out.write("    <li><a href=\"createQueue\">Create queue</a></li>\r\n");
      out.write("    <li><a href=\"viewQueues\">View Queues</a></li>\r\n");
      out.write("    <li><a href=\"updateQueue\">Update Queues</a></li>\r\n");
      out.write("    <li><a href=\"addMeInQueue\">Add me in queue</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
