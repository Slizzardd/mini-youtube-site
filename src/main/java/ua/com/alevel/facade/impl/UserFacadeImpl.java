package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.web.dto.request.UserRequestDto;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setLogin(userRequestDto.getLogin());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setPassword(userRequestDto.getPassword());
        userService.create(user);
    }

    @Override
    public void delete(Long id) {

    }
}
