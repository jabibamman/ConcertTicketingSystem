-- Ajout de niveaux de priorité
INSERT INTO priority (label, priority_value) VALUES
                                                 ('VIP', 1),
                                                 ('Standard', 2);

-- Ajout de concerts
INSERT INTO concert (name, date, total_tickets, remaining_tickets) VALUES
                                                                       ('Rock Fest', '2024-03-01 20:00:00', 500, 500),
                                                                       ('Jazz Night', '2024-04-15 19:30:00', 300, 300);

-- Ajout d'utilisateurs
INSERT INTO "user" (name, email, priority_level) VALUES
                                                     ('Alice', 'alice@example.com', 1), -- VIP
                                                     ('Bob', 'bob@example.com', 2);    -- Standard

-- Exemple de réservation
INSERT INTO reservation (user_id, concert_id, ticket_number) VALUES
                                                                 (1, 1, 1),  -- Alice réserve un billet pour Rock Fest
                                                                 (2, 2, 1);  -- Bob réserve un billet pour Jazz Night
