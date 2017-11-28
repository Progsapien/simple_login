package start;

import com.google.gson.Gson;
import dao.implementations.AccessTokenDAO;
import dao.implementations.UserDAO;
import dao.interfaces.IAccessTokenDAO;
import dao.interfaces.IUserDAO;
import model.implementations.User;
import model.interfaces.IAccessToken;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import service.implementations.LoginService;
import service.implementations.RegisterService;
import service.interfaces.IRegisterService;

import java.util.UUID;


public class Start {
    public static void main(String[] args) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();

        IUserDAO userDAO = new UserDAO();
        IAccessTokenDAO accessTokenDAO = new AccessTokenDAO();

        Gson json = new Gson();

        ((UserDAO)userDAO).setSession(session);
        ((AccessTokenDAO)accessTokenDAO).setSession(session);

        LoginService loginService = new LoginService();
        loginService.setUserDAO(userDAO);
        loginService.setAccessTokenDAO(accessTokenDAO);

        IRegisterService registerService = new RegisterService();
        ((RegisterService)registerService).setLoginService(loginService);
        ((RegisterService)registerService).setUserDAO(userDAO);


        IAccessToken token = loginService.login("valentin112", "password111");
        if(token != null) {
            System.out.println(json.toJson(token));
        } else {
            System.out.println(":(");
        }

    }
}
