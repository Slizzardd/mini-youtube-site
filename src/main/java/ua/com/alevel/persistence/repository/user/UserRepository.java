package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
}
