package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByLogin(String login);

    User findByEmail(String email);
}
