package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.channel.User;

import java.util.Optional;

public interface UserService extends BaseService<User>{

    Optional<User> findByEmail(String userEmail);
}
