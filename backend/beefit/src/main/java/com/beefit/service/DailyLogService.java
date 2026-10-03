package com.beefit.service;

import com.beefit.entity.DailyLog;
import com.beefit.repository.DailyLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DailyLogService {

    private final DailyLogRepository dailyLogRepository;

    public DailyLogService(DailyLogRepository dailyLogRepository) {
        this.dailyLogRepository = dailyLogRepository;
    }

    public DailyLog createDailyLog(LocalDate date) {

        Optional<DailyLog> existingLog =
                dailyLogRepository.findByLogDate(date);

        if (existingLog.isPresent()) {
            return existingLog.get();
        }

        DailyLog dailyLog = new DailyLog(date);

        return dailyLogRepository.save(dailyLog);
    }

    public List<DailyLog> getAllDailyLogs() {
        return dailyLogRepository.findAll();
    }

    public Optional<DailyLog> getDailyLogById(Long id) {
        return dailyLogRepository.findById(id);
    }

    public Optional<DailyLog> getDailyLogByDate(LocalDate date) {
        return dailyLogRepository.findByLogDate(date);
    }

    public void deleteDailyLog(Long id) {
        dailyLogRepository.deleteById(id);
    }
}
