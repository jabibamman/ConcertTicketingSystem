-- Table concert : informations sur les concerts
CREATE TABLE concert (
                         id SERIAL PRIMARY KEY,             -- Identifiant unique du concert
                         name VARCHAR(255) NOT NULL,        -- Nom du concert
                         date TIMESTAMP NOT NULL,           -- Date et heure du concert
                         total_tickets INT NOT NULL,        -- Nombre total de billets disponibles
                         remaining_tickets INT NOT NULL,    -- Nombre de billets restants
                         created_at TIMESTAMP DEFAULT NOW() -- Date de création
);

-- Table user : informations sur les utilisateurs
CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,             -- Identifiant unique de l'utilisateur
                        name VARCHAR(255) NOT NULL,        -- Nom de l'utilisateur
                        email VARCHAR(255) UNIQUE NOT NULL,-- Email unique
                        priority_level INT NOT NULL,       -- Niveau de priorité (référence à la table priority)
                        created_at TIMESTAMP DEFAULT NOW() -- Date de création
);

-- Table priority : gestion des niveaux de priorité
CREATE TABLE priority (
                          id SERIAL PRIMARY KEY,             -- Identifiant unique de la priorité
                          label VARCHAR(50) NOT NULL UNIQUE, -- Nom du niveau de priorité (ex. : VIP, Standard)
                          priority_value INT NOT NULL        -- Valeur numérique (1 = VIP, 2 = Standard, etc.)
);

-- Table reservation : gestion des réservations
CREATE TABLE reservation (
                             id SERIAL PRIMARY KEY,             -- Identifiant unique de la réservation
                             user_id INT NOT NULL,              -- Référence à l'utilisateur
                             concert_id INT NOT NULL,           -- Référence au concert
                             ticket_number INT NOT NULL,        -- Numéro unique du billet réservé
                             reserved_at TIMESTAMP DEFAULT NOW(), -- Date de la réservation
                             FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
                             FOREIGN KEY (concert_id) REFERENCES concert (id) ON DELETE CASCADE
);
