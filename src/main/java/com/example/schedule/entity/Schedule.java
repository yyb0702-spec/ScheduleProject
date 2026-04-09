package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table (name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String name;
    private String password;

    public Schedule(String title,String content,String name,String password)
    {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }

    public void updateSchedule(String title,String content,String name,String password)
    {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }

}
