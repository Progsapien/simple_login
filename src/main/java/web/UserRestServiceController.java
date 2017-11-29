package web;

import com.google.gson.Gson;
import dao.implementations.AccessTokenDAO;
import dao.implementations.UserDAO;
import dao.interfaces.IAccessTokenDAO;
import dao.interfaces.IUserDAO;
import model.interfaces.IAccessToken;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import service.implementations.LoginService;
import service.interfaces.ILoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRestServiceController extends HttpServlet {

    IUserDAO userDAO;
    IAccessTokenDAO accessTokenDAO;
    Gson gson;
    ILoginService loginService;

    public UserRestServiceController() {

        Session session = new Configuration().configure().buildSessionFactory().openSession();
        userDAO = new UserDAO();
        accessTokenDAO = new AccessTokenDAO();
        loginService = new LoginService();

        ((UserDAO)userDAO).setSession(session);
        ((LoginService)loginService).setUserDAO(userDAO);
        ((LoginService)loginService).setAccessTokenDAO(accessTokenDAO);
        ((AccessTokenDAO)accessTokenDAO).setSession(session);

        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getServletPath());
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.getWriter().write(gson.toJson(userDAO.getAll()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println(req.getServletPath());
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
        if(req.getServletPath().equals("/user/login")) {
            System.out.println("IN LOGIN");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            resp.getWriter().append(gson.toJson(loginService.login(username, password)));
        }
    }
}
