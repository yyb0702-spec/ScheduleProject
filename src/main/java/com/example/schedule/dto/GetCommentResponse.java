package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final Long id;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long scheduleId;

    public GetCommentResponse(Long id, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt, Long scheduleId) {
        this.id = id;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.scheduleId = scheduleId;
    }
}
