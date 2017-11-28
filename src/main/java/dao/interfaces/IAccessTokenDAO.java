package dao.interfaces;

import model.interfaces.IAccessToken;

import java.util.List;

public interface IAccessTokenDAO {
    void add(IAccessToken IAccessToken);
    void update(IAccessToken IAccessToken);
    void remove(IAccessToken IAccessToken);
    List<IAccessToken> getAll();
    IAccessToken getTokenByUserId(int id);
    boolean contains(IAccessToken accessToken);
}
