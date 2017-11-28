package dao.implementations;

import dao.interfaces.IUserRolesDAO;
import model.interfaces.IUserRoles;
import org.hibernate.Session;

import java.util.List;

public class UserRolesDAO implements IUserRolesDAO {

    Session session;

    public void add(IUserRoles IUserRoles) {
        session.beginTransaction();
        session.save(IUserRoles);
        session.getTransaction().commit();
    }

    public void update(IUserRoles IUserRoles) {
        session.beginTransaction();
        session.update(IUserRoles);
        session.getTransaction().commit();
    }

    public void remove(IUserRoles IUserRoles) {
        session.beginTransaction();
        session.remove(IUserRoles);
        session.getTransaction().commit();

    }

    public List<IUserRoles> getAll() {
        return session.createQuery("from UserRoles ").list();
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
