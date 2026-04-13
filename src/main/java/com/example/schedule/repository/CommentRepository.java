package com.example.schedule.repository;

import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    long countByScheduleId(Long scheduleId); //Select count(scheduleId) from comment
    List<Comment> findByScheduleId(Long scheduleId); //Select * from comment where scheduleId = scheduleId
}
