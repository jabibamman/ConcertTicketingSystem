package esgi.fyc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import esgi.fyc.entity.User;
import esgi.fyc.entity.UserRequest;
import esgi.fyc.service.ConcertBookingOrchestrator;

public class ConcertBookingOrchestratorTest {
    
    @Test
    void testConcurrentBooking() {
        ConcertBookingOrchestrator orchestrator = new ConcertBookingOrchestrator(100, 10);
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();
        Long concertId = 1L;
        
        for (int i = 0; i < 150; i++) {
            User user = new User();  
            user.setId(ThreadLocalRandom.current().nextInt(1, 3));
            user.setName("User" + i);
            user.setEmail("user" + i + "@test.com");
            user.setPriorityLevel(i % 3);
            
            UserRequest request = new UserRequest(
                user,
                concertId,
                i % 3,  // priority
                LocalDateTime.now()
            );
            
            futures.add(orchestrator.processBookingRequest(request));
        }
        
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        
        long successfulBookings = futures.stream()
            .map(CompletableFuture::join)
            .filter(success -> success)
            .count();
        
        assertEquals(100, successfulBookings);
    }
}