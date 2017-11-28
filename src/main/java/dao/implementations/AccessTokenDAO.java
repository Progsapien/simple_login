package dao.implementations;

import dao.interfaces.IAccessTokenDAO;
import model.implementations.AccessToken;
import model.interfaces.IAccessToken;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AccessTokenDAO implements IAccessTokenDAO {

    Session session;

    public void add(IAccessToken IAccessToken) {
        session.beginTransaction();
        session.save(IAccessToken);
        session.getTransaction().commit();
    }

    public void update(IAccessToken IAccessToken) {
        session.beginTransaction();
        session.update(IAccessToken);
        session.getTransaction().commit();
    }

    public void remove(IAccessToken IAccessToken) {
        session.beginTransaction();
        session.remove(IAccessToken);
        session.getTransaction().commit();
    }

    public List<IAccessToken> getAll() {
        return session.createQuery("from AccessToken").list();
    }

    public IAccessToken getTokenByUserId(int id) {
        Criteria criteria = session.createCriteria(AccessToken.class);
        criteria.add(Restrictions.eq("userId", id));
        return (IAccessToken) criteria.uniqueResult();
    }

    public boolean contains(IAccessToken accessToken) {
        Criteria criteria = session.createCriteria(AccessToken.class);
        criteria.add(Restrictions.eq("accessToken", accessToken.getAccessToken()));
        if(criteria.uniqueResult() != null) {
            return true;
        }
        return false;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
