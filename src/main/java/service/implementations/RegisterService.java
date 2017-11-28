package service.implementations;

import dao.interfaces.IUserDAO;
import model.interfaces.IAccessToken;
import model.interfaces.IUser;
import service.interfaces.ILoginService;
import service.interfaces.IRegisterService;

public class RegisterService implements IRegisterService {
    IUserDAO userDAO;
    ILoginService loginService;

    public IAccessToken register(IUser user) {
        IAccessToken token = null;
        if(userDAO.getByUsername(user.getUserName()) == null) {
            userDAO.add(user);
            token = loginService.login(user.getUserName(), user.getPassword());
        }
        return token;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }
}
