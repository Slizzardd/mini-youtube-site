package ua.com.alevel.facade;

import ua.com.alevel.web.dto.request.UserRequestDto;
import ua.com.alevel.web.dto.response.UserResponseDto;

public interface UserFacade extends BaseFacade<UserRequestDto, UserResponseDto> {

    void create(UserRequestDto userRequestDto);

    void update(Long id, UserRequestDto userRequestDto);
    void delete(Long userId, String userEmail);
    UserResponseDto findById(Long id);
}
