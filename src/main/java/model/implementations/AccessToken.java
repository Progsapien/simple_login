package model.implementations;

import model.interfaces.IAccessToken;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class AccessToken implements IAccessToken {

    @Id
    int userId;
    String accessToken;

    public AccessToken() {
        this.accessToken = UUID.randomUUID().toString();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
