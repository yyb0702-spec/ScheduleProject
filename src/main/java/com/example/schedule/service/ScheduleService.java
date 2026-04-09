package com.example.schedule.service;

import com.example.schedule.dto.*;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword());
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(saveSchedule.getId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getName(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt());
    }
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long scheduleId)
    {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 스케쥴 입니다")
        );

        return new GetOneScheduleResponse(schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAll(String name)
    {
        List<Schedule> schedules;

        if (name == null) {
            schedules = scheduleRepository.findAll();
        } else {
            schedules = scheduleRepository.findByName(name);
        }


        List<GetOneScheduleResponse> dtos = new ArrayList<>();
        for(Schedule schedule : schedules)
        {
            GetOneScheduleResponse dto = new GetOneScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId , UpdateScheduleRequest request)
    {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 스케쥴 입니다")
        );
        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        schedule.updateSchedule(request.getTitle(),request.getName());

        return new UpdateScheduleResponse(schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getModifiedAt());
    }

    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        String password = request.getPassword();

        if (!request.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        boolean existance = scheduleRepository.existsById(scheduleId);

        if(!existance)
        {
            throw new IllegalStateException("없는 유저 입니다");
        }

        scheduleRepository.deleteById(scheduleId);
    }
}
