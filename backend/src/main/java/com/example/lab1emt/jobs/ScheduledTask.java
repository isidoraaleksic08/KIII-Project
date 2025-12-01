package com.example.lab1emt.jobs;

import com.example.lab1emt.service.application.MaterializedViewRefresherInterface;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class ScheduledTask {

    private final MaterializedViewRefresherInterface materializedViewRefresher;

    public ScheduledTask(MaterializedViewRefresherInterface materializedViewRefresher) {
        this.materializedViewRefresher = materializedViewRefresher;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        materializedViewRefresher.refreshBooksByAuthorView();
        System.out.println("Materialized view 'books_by_author' е успешно освежен.");
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void anotherScheduledTask() {
        System.out.println("Извршувам дневна задача.");
    }
}
