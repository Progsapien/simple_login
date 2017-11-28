package model.interfaces;

public interface IUser {
    String getUserName();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();
    int getId();

    void setEmail(String email);
    void setUserName(String userName);
    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setPassword(String password);
    void setId(int id);
}
