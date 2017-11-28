package dao.interfaces;

import model.interfaces.IUserRoles;

import java.util.List;

public interface IUserRolesDAO {
    void add(IUserRoles IUserRoles);
    void update(IUserRoles IUserRoles);
    void remove(IUserRoles IUserRoles);
    List<IUserRoles> getAll();
}
