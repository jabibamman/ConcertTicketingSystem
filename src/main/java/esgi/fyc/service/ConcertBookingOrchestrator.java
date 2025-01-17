package esgi.fyc.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

import esgi.fyc.entity.UserRequest;

public class ConcertBookingOrchestrator {
    private final TicketingService ticketingService;
    private final NotificationService notificationService;
    private final Semaphore concurrentBookings;

    public ConcertBookingOrchestrator(int totalTickets, int maxConcurrentBookings) {
        this.ticketingService = new TicketingService(totalTickets);
        this.notificationService = new NotificationService();
        this.concurrentBookings = new Semaphore(maxConcurrentBookings);
    }

    public CompletableFuture<Boolean> processBookingRequest(UserRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                concurrentBookings.acquire();
                return ticketingService.processRequest(request)
                    .thenCompose(success -> 
                        notificationService.notifyUser(request.getUser(), success)
                            .thenApply(v -> success)
                    ).join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            } finally {
                concurrentBookings.release();
            }
        });
    }
} 