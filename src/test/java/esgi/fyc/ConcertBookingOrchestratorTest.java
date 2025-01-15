package esgi.fyc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

import esgi.fyc.entity.User;
import esgi.fyc.entity.UserRequest;
import esgi.fyc.service.ConcertBookingOrchestrator;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ConcertBookingOrchestratorTest {
    
    // Résultat attendu : 100 réservations réussies et 50 non dispo
    @Test
    void testConcurrentBooking() {
        ConcertBookingOrchestrator orchestrator = new ConcertBookingOrchestrator(100, 10);
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();
        Long concertId = 1L;

        // TODO: Créer 150 utilisateurs avec des ID et des emails uniques
        for (int i = 0; i < 150; i++) {
            // TODO: Initialiser un utilisateur avec un ID unique
            // TODO: Créer une requête utilisateur avec une priorité aléatoire et l'ajouter à la liste des futures
        }


        // TODO: Attendre la complétion de toutes les futures et collecter les résultats
        // TODO: Compter le nombre de réservations réussies

        assertEquals(100, /*TODO: remplacer par le nombre réel de succès de booking*/ 0 );
    }
}