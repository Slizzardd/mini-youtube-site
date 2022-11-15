package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CrudRepositoryHelper<User, UserRepository> crudRepositoryHelper;

    public UserServiceImpl(UserRepository userRepository, CrudRepositoryHelper<User, UserRepository> crudRepositoryHelper) {
        this.userRepository = userRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public void create(User user) {
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
