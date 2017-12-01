package web;

import com.google.gson.Gson;
import dao.implementations.AccessTokenDAO;
import dao.implementations.UserDAO;
import dao.interfaces.IAccessTokenDAO;
import dao.interfaces.IUserDAO;
import model.implementations.User;
import model.interfaces.IAccessToken;
import model.interfaces.IUser;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import service.implementations.LoginService;
import service.implementations.RegisterService;
import service.interfaces.ILoginService;
import service.interfaces.IRegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRestServiceController extends HttpServlet {

    IUserDAO userDAO;
    IAccessTokenDAO accessTokenDAO;
    Gson gson;
    ILoginService loginService;
    IRegisterService registerService;

    public UserRestServiceController() {

        Session session = new Configuration().configure().buildSessionFactory().openSession();
        userDAO = new UserDAO();
        accessTokenDAO = new AccessTokenDAO();
        loginService = new LoginService();
        registerService = new RegisterService();

        ((UserDAO)userDAO).setSession(session);
        ((LoginService)loginService).setUserDAO(userDAO);
        ((LoginService)loginService).setAccessTokenDAO(accessTokenDAO);
        ((AccessTokenDAO)accessTokenDAO).setSession(session);
        ((RegisterService)registerService).setUserDAO(userDAO);
        ((RegisterService)registerService).setLoginService(loginService);

        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");

        String token = req.getRequestURI();
        token = token.substring(token.lastIndexOf("/")+1);
        IAccessToken accessToken = accessTokenDAO.getToken(token);
        if(accessToken != null) {
            resp.getWriter().write(gson.toJson(userDAO.getById(accessToken.getUserId())));
        } else {
            resp.getWriter().write("null");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        if(req.getServletPath().equals("/user/login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            if(username != null & password != null) {
                IAccessToken token = loginService.login(username, password);
                //resp.addCookie(new Cookie("accessToken", token.getAccessToken()));
                resp.getWriter().write(gson.toJson(token));
            } else {
                Cookie cookies[] = req.getCookies();
                String accessToken = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName() == "accessToken") {
                        accessToken = cookie.getValue();
                        break;
                    }
                }
                if (accessToken != null) {
                    // log in with token;
                }
            }
        } else if(req.getServletPath().equals("/user/register")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");

            if(username != null & password != null & email != null & firstname != null & lastname != null) {
                IUser user = new User();
                user.setEmail(email);
                user.setFirstName(firstname);
                user.setUserName(username);
                user.setLastName(lastname);
                user.setPassword(password);

                IUser registered_user = registerService.register(user);
                resp.getWriter().write(gson.toJson(registered_user));
            }
        }
    }
}
