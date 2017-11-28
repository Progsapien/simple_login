package model.interfaces;

public interface IUserRoles {
    enum Role { Admin, User, Guest}

    int getUserId();
    Role getUserRole();

    void setUserId(int id);
    void setUserRole(Role role);

}
