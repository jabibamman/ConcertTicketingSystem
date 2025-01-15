package esgi.fyc.service;
import java.util.concurrent.CompletableFuture;

import esgi.fyc.entity.User;
public class NotificationService {
    public CompletableFuture<Void> notifyUser(User user, boolean success) {
        return CompletableFuture.runAsync(() -> {
            String message = success 
                ? "Félicitations votre billet a été réservé avec succès."
                : "Désolé la réservation a échoué. Plus de billets disponibles.";
            
            System.out.printf("Notification pour %s (%s): %s%n", 
                user.getName(), user.getEmail(), message);
        });
    }
}