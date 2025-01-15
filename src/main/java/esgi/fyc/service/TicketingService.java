package esgi.fyc.service;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.StampedLock;

import esgi.fyc.entity.Reservation;
import esgi.fyc.entity.UserRequest;
import esgi.fyc.repository.ReservationRepository;

public class TicketingService {
    private final PriorityBlockingQueue<UserRequest> requestQueue;
    private final AtomicInteger availableTickets;
    private final StampedLock ticketLock;
    private final ReservationRepository reservationRepository;
    
    public TicketingService(int initialTickets) {
        this.requestQueue = new PriorityBlockingQueue<>();
        this.availableTickets = new AtomicInteger(initialTickets);
        this.ticketLock = new StampedLock();
        this.reservationRepository = new ReservationRepository();
    }

    private boolean reserveTicket(UserRequest request) {
        long stamp = ticketLock.writeLock();
        try {
            if (availableTickets.decrementAndGet() >= 0) {
                Reservation reservation = new Reservation();
                reservation.setUserId(request.getUser().getId());
                reservation.setConcertId(request.getConcertId().intValue());
                reservation.setTicketNumber(1);
                reservation.setReservedAt(LocalDateTime.now());
                
                reservationRepository.save(reservation);
                return true;
            }
            availableTickets.incrementAndGet();
            return false;
        } finally {
            ticketLock.unlockWrite(stamp);
        }
    }

    public CompletableFuture<Boolean> processRequest(UserRequest request) {
        requestQueue.offer(request);
        return CompletableFuture.supplyAsync(() -> processTicketRequest(request));
    }

    private boolean processTicketRequest(UserRequest request) {
        try {
            while (requestQueue.peek() != request) {
                Thread.sleep(100);
            }
            
            if (areTicketsAvailable()) {
                return reserveTicket(request);
            }
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            requestQueue.remove(request);
        }
    }

    private boolean areTicketsAvailable() {
        long stamp = ticketLock.tryOptimisticRead();
        int currentTickets = availableTickets.get();
        
        if (!ticketLock.validate(stamp)) {
            stamp = ticketLock.readLock();
            try {
                currentTickets = availableTickets.get();
            } finally {
                ticketLock.unlockRead(stamp);
            }
        }
        
        return currentTickets > 0;
    }
}