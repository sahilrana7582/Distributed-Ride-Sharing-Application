package com.example.user_service.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadSchedular {

    private static volatile ThreadSchedular threadSchedular;  // Singleton instance
    private final ScheduledExecutorService scheduledExecutorService; // Encapsulated scheduler

    private ThreadSchedular() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1); // Initialize in constructor
    }

    public static ThreadSchedular getInstance() {
        if (threadSchedular == null) {  // First check
            synchronized (ThreadSchedular.class) {  // Synchronize block
                if (threadSchedular == null) {  // Second check
                    threadSchedular = new ThreadSchedular();
                }
            }
        }
        return threadSchedular;
    }

    public ScheduledExecutorService getScheduler() {
        return scheduledExecutorService;
    }
}
