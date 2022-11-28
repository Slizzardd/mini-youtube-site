package ua.com.alevel.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.HelpService;
import ua.com.alevel.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CrudRepositoryHelper<User, UserRepository> crudRepositoryHelper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final HelpService<User> helpService;

    public UserServiceImpl(UserRepository userRepository,
                           CrudRepositoryHelper<User, UserRepository> crudRepositoryHelper,
                           BCryptPasswordEncoder bCryptPasswordEncoder, HelpService<User> helpService) {
        this.userRepository = userRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.helpService = helpService;
    }

    @Override
    public void create(User user) throws EntityExistException{
        if (userRepository.existsByEmail(user.getEmail())
                || userRepository.existsByLogin(user.getLogin())) {
            throw new EntityExistException("this personal is exist");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        crudRepositoryHelper.create(userRepository, user);
    }

    @Override
    public void update(User user) {
        crudRepositoryHelper.update(userRepository, user);
    }

    @Override
    public void delete(User user) {
        crudRepositoryHelper.delete(userRepository, user.getId());
        helpService.deletingFileWhenDeleteEntity(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElse(null);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }
}
