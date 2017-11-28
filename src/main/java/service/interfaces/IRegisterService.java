package service.interfaces;

import model.interfaces.IAccessToken;
import model.interfaces.IUser;

public interface IRegisterService {
    IAccessToken register(IUser user);
}
