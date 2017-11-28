package web;

import com.google.gson.Gson;
import dao.implementations.UserDAO;
import dao.interfaces.IUserDAO;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestController extends HttpServlet {

    IUserDAO userDAO;
    Gson gson;

    public RestController() {

        Session session = new Configuration().configure().buildSessionFactory().openSession();
        userDAO = new UserDAO();
        ((UserDAO)userDAO).setSession(session);
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getServletPath());
        System.out.println(req.getContextPath());
        resp.getWriter().write(gson.toJson(userDAO.getAll()));
    }
}
