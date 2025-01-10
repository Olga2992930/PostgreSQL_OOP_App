package se.deved.command;

import java.util.Scanner;

import se.deved.Application;

import java.sql.PreparedStatement;
import java.sql.SQLException;
//import se.deved.command;

public class CompleteCommand extends Command {

    public CompleteCommand(Application application) {
        super("complete", "avsluta transaktionen", application);
    }

    @Override
    public void execute(String[] commandArgs) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ange transaktionsnummer: ");
        int numret = scanner.nextInt();
        scanner.nextLine();

        try (PreparedStatement updateStatement = Application.conn.prepareStatement("UPDATE financetable SET completed = true WHERE id = ?")) {
            updateStatement.setInt(1, numret);

            if (updateStatement.executeUpdate() == 0) {
                System.out.println("Finns ingen transaktion med  id " + numret);
                return;
            }
        } catch (SQLException exception) {
            System.out.println("Failed to update todo.");
            return;
        }

        System.out.println("Marked todo as completed.");

    }
}
