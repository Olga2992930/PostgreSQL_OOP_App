package se.deved;

/*

<-----> En personal-finance applikation <----->

Funktionalitet:

I applikationen kan man göra följande:

- Lägga in transaktioner (manuellt; när du exempelvis har köpt något eller fått lön)

- Se alla transaktioner

- Radera transaktioner (manuellt)

 */

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;


public class Application {

    private MenuManager menuManager;
    private SaveManager saveManager;
    private boolean running;
    public static Connection conn;

    public Application() {
        this.menuManager = new SimpleMenuManager(this);
        this.running = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv 'help' för en lista på kommandon.");
    }

    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost/exampledatabase?user=postgres&password=password";
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return;
        }

        try {
            Statement createTablesStatement = conn.createStatement();
            createTablesStatement.execute("CREATE TABLE IF NOT EXISTS financetable (" +
                    "id SERIAL PRIMARY KEY," +
                    "completed BOOLEAN NOT NULL DEFAULT false," +
                    "beskrivning TEXT NOT NULL," +
                    "belopp INT, " +
                    "datum DATE)");
        } catch (SQLException exception) {
            exception.printStackTrace();
            return;
        }

        Application application = new Application();
        Scanner scanner = new Scanner(System.in);
        while (application.isRunning()) {
            application.getMenuManager().getCurrentMenu().tryExecuteCommand(scanner.nextLine());
        }
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }

    public SaveManager getTransactionManager() {
        return saveManager;
    }
}