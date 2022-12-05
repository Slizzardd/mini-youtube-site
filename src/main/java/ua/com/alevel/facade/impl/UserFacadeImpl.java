package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.web.dto.request.UserRequestDto;
import ua.com.alevel.web.dto.response.UserResponseDto;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) throws EntityExistException {
        User user = new User();
        setMainUserInformation(userRequestDto, user);
        userService.create(user);
    }

    @Override
    public void update(Long id, UserRequestDto userRequestDto) {
        User user = userService.findById(id);
        if (ObjectUtils.isNotEmpty(user)) {
            setMainUserInformation(userRequestDto, user);
            userService.update(user);
        }
    }

    @Override
    public void delete(Long id) {
        userService.delete(userService.findById(id));
    }

    @Override
    public UserResponseDto findById(Long id) {
        return new UserResponseDto(userService.findById(id));
    }

    private void setMainUserInformation(UserRequestDto userRequestDto, User user) {
        user.setLogin(userRequestDto.getLogin());
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setPassword(userRequestDto.getPassword());
    }
}
