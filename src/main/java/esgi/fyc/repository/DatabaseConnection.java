package esgi.fyc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a utility method for establishing a connection to the database.
 *
 * <p>This class manages the connection details, including the hostname, port,
 * database name, user, and password. It uses the PostgreSQL JDBC driver to
 * establish a connection.</p>
 */
public class DatabaseConnection {

    /** The hostname of the database server. Defaults to localhost. */
    private static final String HOSTNAME = "localhost";

    /** The port number on which the database server is running. Defaults to 5432 for PostgreSQL. */
    private static final String PORT = "5432";

    /** The name of the database to connect to. */
    private static final String DATABASE = "concert_ticketing";

    /**
     * The URL of the PostgreSQL database, constructed using the hostname, port,
     * and database name.
     */
    private static final String URL = String.format("jdbc:postgresql://%s:%s/%s", HOSTNAME, PORT, DATABASE);

    /** The username for connecting to the database. */
    private static final String USER = "admin";

    /** The password for connecting to the database. */
    private static final String PASSWORD = "admin";

    /**
     * Establishes and returns a connection to the database.
     *
     * @return a {@link Connection} object for interacting with the database.
     * @throws SQLException if a database access error occurs or the connection fails.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}