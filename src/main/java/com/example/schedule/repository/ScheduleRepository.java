package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findAllByOrderByModifiedAtDesc(); //select * from schedule order by modified desc
    List<Schedule> findByNameOrderByModifiedAtDesc(String name); // select * from schedule where name = name order by modified desc
}
