package se.deved.command;

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
        TransactionsManager manager = new TransactionsManager();
        manager.visaAllaTransactioner();

        if (!manager.getTransactioner().isEmpty()) {
            System.out.print("Ange transaktionsnumret att ta bort: ");
            int numret = scanner.nextInt() - 1;
            if (numret >= 0 && numret < manager.getTransactioner().size()) {
                manager.getTransactioner().remove(numret);
                System.out.println("Transaktionen har tagits bort.");
            } else {
                System.out.println("Ogiltigt transaktionsnummer.");
            }

            manager.visaAllaTransactioner();

        }

    }
}
