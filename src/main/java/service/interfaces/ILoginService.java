package service.interfaces;

import model.interfaces.IAccessToken;

public interface ILoginService {
    IAccessToken login(String username, String password);
    void logout(IAccessToken token);
}
