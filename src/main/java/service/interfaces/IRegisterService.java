package service.interfaces;

import model.interfaces.IAccessToken;
import model.interfaces.IUser;

public interface IRegisterService {
    IUser register(IUser user);
}
