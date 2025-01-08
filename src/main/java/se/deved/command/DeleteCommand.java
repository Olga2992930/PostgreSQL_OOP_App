package se.deved.command;

import java.sql.*;
import java.util.Scanner;
import se.deved.Application;
import se.deved.TransactionsManager;

public class DeleteCommand extends Command {

    public DeleteCommand(Application application) {
        super("delete", "radera en transaktion", application);
    }
    @Override
    public void execute(String[] commandArgs) {
        Scanner scanner = new Scanner(System.in);

        try (Statement selectStatement = Application.conn.createStatement()) {
            try (ResultSet result = selectStatement.executeQuery("SELECT * FROM transactionstable")) {
                System.out.println("Här är alla transaktioner: ");
                while (result.next()) {
                    //displayTodo(result);
                    int id = result.getInt("id");
                    String beskrivning = result.getString("beskrivning");
                    int belopp = result.getInt("belopp");
                    Date datum = result.getDate("datum");
                    System.out.println("- (" + id + ") " + beskrivning);
                    System.out.println("  Belopp: " + belopp);
                    System.out.println("  Datum: " + datum);
                }
            }
        } catch (SQLException exception) {
            System.out.println("Failed to fetch todos from database.");
            return;
        }

        System.out.print("Ange transaktionsnumret att ta bort: ");
        int numret = scanner.nextInt();
        scanner.nextLine();

        try (PreparedStatement deleteStatement = Application.conn.prepareStatement("DELETE FROM transactionstable WHERE id = ?")) {
            deleteStatement.setInt(1, numret);

            if (deleteStatement.executeUpdate() == 0) {
                System.out.println("Ogiltigt transaktionsnummer " + numret);
                return;
            }
        } catch (SQLException exception) {
            System.out.println("Failed to delete transaction.");
            return;
        }

        System.out.println("Deleted todo from database.");

        //TransactionsManager manager = new TransactionsManager();
        //manager.visaAllaTransactioner();

        //if (!manager.getTransactioner().isEmpty()) {
          //  System.out.print("Ange transaktionsnumret att ta bort: ");
            //int numret = scanner.nextInt() - 1;
            //if (numret >= 0 && numret < manager.getTransactioner().size()) {
                //.getTransactioner().remove(numret);
               // System.out.println("Transaktionen har tagits bort.");
            //} else {
              //  System.out.println("Ogiltigt transaktionsnummer.");
          //  }

           // manager.visaAllaTransactioner();

        //}

    }




}

