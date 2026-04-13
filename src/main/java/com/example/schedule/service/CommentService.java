package com.example.schedule.service;

import com.example.schedule.dto.CreateCommentRequest;
import com.example.schedule.dto.CreateCommentResponse;
import com.example.schedule.entity.Comment;
import com.example.schedule.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) {

        long count = commentRepository.countByScheduleId(scheduleId); //scheduleId 로 count를 찾는 select 쿼리 실행

        if(count >= 10)
        {
            throw new IllegalArgumentException("댓글은 최대 10개까지만 작성하실수 있습니다");
        }

        Comment comment = new Comment(request.getContent(),request.getName(),request.getPassword(),scheduleId);
        Comment saveComment = commentRepository.save(comment);

        return new CreateCommentResponse(saveComment.getId(),
                saveComment.getScheduleId(),
                saveComment.getContent(),
                saveComment.getName(),
                saveComment.getCreatedAt(),
                saveComment.getModifiedAt());
    }
}
