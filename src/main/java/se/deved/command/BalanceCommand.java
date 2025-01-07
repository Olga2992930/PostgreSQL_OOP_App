package se.deved.command;


//import java.util.Scanner;

import se.deved.Application;
import se.deved.TransactionsManager;
//import se.deved.command;

public class BalanceCommand extends Command {

    public BalanceCommand(Application application) {
        super("balance", "visa saldo", application);
    }

    @Override
    public void execute(String[] commandArgs) {
       // Scanner scanner = new Scanner(System.in);
        TransactionsManager manager = new TransactionsManager();
        manager.visaSaldo();

       
    }
}
