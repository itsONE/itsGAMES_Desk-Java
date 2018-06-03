package utfpr.itsone.model.interfaces;

import utfpr.itsone.model.User;

import java.util.List;

public interface ImplementUser {
    void insert(User user);

    void update(User user);

    void delete(int id);

    List<User> getUserForName(String name);

    List<User> getUserForEmail(String email);

    User getUserForID(int id);

    List<User> getAllUsers();
}
