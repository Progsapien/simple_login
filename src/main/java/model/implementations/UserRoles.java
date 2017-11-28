package model.implementations;

import javax.persistence.Entity;
import javax.persistence.Id;
import model.interfaces.IUserRoles;

@Entity
public class UserRoles implements IUserRoles {

    @Id
    int userId;
    Role userRole;

    public int getUserId() {
        return userId;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public void setUserRole(Role role) {
        this.userRole = role;
    }
}
