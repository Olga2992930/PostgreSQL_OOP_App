package se.deved.menu;

import se.deved.Application;
import se.deved.command.CreateCommand;
import se.deved.command.DeleteCommand;
import se.deved.command.ExpensesCommand;
import se.deved.command.IncomeCommand;
import se.deved.command.StopCommand;
import se.deved.command.BalanceCommand;

public class UserMenu extends Menu {

    public UserMenu(Application application) {
        super(application);
        registerCommand(new CreateCommand(application));
        registerCommand(new DeleteCommand(application));
        registerCommand(new BalanceCommand(application));
        registerCommand(new IncomeCommand(application));
        registerCommand(new ExpensesCommand(application));
        registerCommand(new StopCommand(application));
    }

    @Override
    public void display() {
        System.out.println("Välkommen till din personliga finance app!");
        //System.out.println("Skriv 'help' för en lista på kommandon.");
    }
}
