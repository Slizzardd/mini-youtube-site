package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.channel.User;

public interface UserService extends BaseService<User> {

    void delete(User user);

    User findByEmail(String userEmail);
}
