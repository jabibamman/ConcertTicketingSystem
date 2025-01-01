package esgi.fyc;
import java.util.List;
import esgi.fyc.dao.ConcertDAO;
import esgi.fyc.dao.ReservationDAO;
import esgi.fyc.dao.UserDAO;
import esgi.fyc.entity.Concert;
import esgi.fyc.entity.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans le système de billetterie multithreadé !");

        ConcertDAO concertDAO = new ConcertDAO();
        UserDAO userDAO = new UserDAO();
        ReservationDAO reservationDAO = new ReservationDAO();

        System.out.println("\n[INFO] Liste des concerts disponibles :");
        List<Concert> concerts = concertDAO.getAllConcerts();
        for (Concert concert : concerts) {
            System.out.println(" - " + concert.getName() + " le " + concert.getDate() +
                    " (Billets restants : " + concert.getRemainingTickets() + ")");
        }

        System.out.println("\n[INFO] Liste des utilisateurs inscrits :");
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(" - " + user.getName() + " (" + user.getEmail() + ")");
        }

        System.out.println("\n[INFO] Connexion à la base de données et tests terminés !");
    }
}