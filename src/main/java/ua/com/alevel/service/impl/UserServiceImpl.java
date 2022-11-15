package ua.com.alevel.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CrudRepositoryHelper<User, UserRepository> crudRepositoryHelper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           CrudRepositoryHelper<User, UserRepository> crudRepositoryHelper,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void create(User user) {
        if (userRepository.existsByEmail(user.getEmail())
                || userRepository.existsByLogin(user.getLogin())) {
            throw new EntityExistException("this personal is exist");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        crudRepositoryHelper.create(userRepository, user);
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }
}
