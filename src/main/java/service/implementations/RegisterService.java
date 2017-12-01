package service.implementations;

import dao.interfaces.IUserDAO;
import model.interfaces.IAccessToken;
import model.interfaces.IUser;
import org.apache.commons.codec.digest.DigestUtils;
import service.interfaces.ILoginService;
import service.interfaces.IRegisterService;

import java.util.UUID;

public class RegisterService implements IRegisterService {
    IUserDAO userDAO;
    ILoginService loginService;

    public IUser register(IUser user) {
        if(userDAO.getByUsername(user.getUserName()) == null) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            userDAO.add(user);
        } else {
            user = null;
        }
        return user;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }
}
