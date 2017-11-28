package model.interfaces;

public interface IAccessToken {
    int getUserId();
    String getAccessToken();

    void setUserId(int userId);
}
