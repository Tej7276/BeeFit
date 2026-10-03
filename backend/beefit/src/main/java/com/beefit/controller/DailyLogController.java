package com.beefit.controller;

import com.beefit.entity.DailyLog;
import com.beefit.service.DailyLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/daily-logs")
public class DailyLogController {

    private final DailyLogService dailyLogService;

    public DailyLogController(DailyLogService dailyLogService) {
        this.dailyLogService = dailyLogService;
    }

    @PostMapping
    public DailyLog createDailyLog(@RequestParam LocalDate date) {
        return dailyLogService.createDailyLog(date);
    }

    @GetMapping
    public List<DailyLog> getAllDailyLogs() {
        return dailyLogService.getAllDailyLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyLog> getDailyLogById(@PathVariable Long id) {
        return dailyLogService.getDailyLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<DailyLog> getDailyLogByDate(
            @PathVariable LocalDate date) {

        return dailyLogService.getDailyLogByDate(date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyLog(@PathVariable Long id) {

        dailyLogService.deleteDailyLog(id);

        return ResponseEntity.noContent().build();
    }
}
