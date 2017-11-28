package service.implementations;

import dao.interfaces.IAccessTokenDAO;
import dao.interfaces.IUserDAO;
import model.implementations.AccessToken;
import model.interfaces.IAccessToken;
import model.interfaces.IUser;
import org.apache.commons.codec.digest.DigestUtils;
import service.interfaces.ILoginService;

public class LoginService implements ILoginService {

    IUserDAO userDAO;
    IAccessTokenDAO accessTokenDAO;

    public IAccessToken login(String username, String password) {
        IUser user = userDAO.getByUsername(username);
        IAccessToken accessToken = null;
        if(user != null) {
            if(DigestUtils.md5Hex(password).equals(user.getPassword())) {
                accessToken = accessTokenDAO.getTokenByUserId(user.getId());
                if(accessToken != null) {
                    return accessToken;
                } else {
                    accessToken = new AccessToken();
                    accessToken.setUserId(user.getId());
                    accessTokenDAO.add(accessToken);
                }
            }
        }

        return accessToken;
    }

    public void logout(IAccessToken token) {
        if(token != null) {
            if(accessTokenDAO.contains(token)) {
                accessTokenDAO.remove(token);
            }
        }
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setAccessTokenDAO(IAccessTokenDAO accessTokenDAO) {
        this.accessTokenDAO = accessTokenDAO;
    }
}
