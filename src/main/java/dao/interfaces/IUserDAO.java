package dao.interfaces;

import model.interfaces.IUser;

import java.util.List;

public interface IUserDAO {
    void add(IUser IUser);
    void update(IUser IUser);
    void remove(IUser IUser);
    List<IUser> getAll();
    IUser getByUsername(String username);
}
