package ua.com.alevel.facade;

import ua.com.alevel.web.dto.request.CommentRequestDto;
import ua.com.alevel.web.dto.response.CommentResponseDto;

public interface CommentFacade extends BaseFacade<CommentRequestDto, CommentResponseDto> {

    void create(CommentRequestDto commentRequestDto);

    void delete(Long id);
}
