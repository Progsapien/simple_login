package dao.implementations;

import dao.interfaces.IUserDAO;
import model.implementations.User;
import model.interfaces.IUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDAO implements IUserDAO {

    Session session;

    public void add(IUser IUser) {
        session.beginTransaction();
        session.save(IUser);
        session.getTransaction().commit();
    }

    public void update(IUser IUser) {
        session.beginTransaction();
        session.update(IUser);
        session.getTransaction().commit();
    }

    public void remove(IUser IUser) {
        session.beginTransaction();
        session.remove(IUser);
        session.getTransaction().commit();
    }

    public List<IUser> getAll() {
        return session.createQuery("from User ").list();
    }

    public IUser getByUsername(String username) {
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        return (IUser) criteria.uniqueResult();
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
