package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.TimerLog;

public interface TimerLogRepository extends JpaRepository<TimerLog, Integer>{

}
