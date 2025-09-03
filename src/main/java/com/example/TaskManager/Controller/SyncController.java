package com.example.TaskManager.Controller;

import com.example.TaskManager.DTO.SyncResponseDto;
import com.example.TaskManager.DTO.TaskDto;
import com.example.TaskManager.Service.SyncService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sync")
public class SyncController {

    @Autowired
    private SyncService syncService;

    @PostMapping("/batch")
    public ResponseEntity<SyncResponseDto> syncBatch(@Valid @RequestBody List<TaskDto> tasks) {
        SyncResponseDto syncResult = syncService.processSyncBatch(tasks);
        return ResponseEntity.ok(syncResult);
    }
}
