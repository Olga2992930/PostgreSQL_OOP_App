package se.deved.menu;

import se.deved.Application;
import se.deved.command.CreateCommand;
import se.deved.command.DeleteCommand;
import se.deved.command.CompleteCommand;
import se.deved.command.StopCommand;

public class UserMenu extends Menu {

    public UserMenu(Application application) {
        super(application);
        registerCommand(new CreateCommand(application));
        registerCommand(new DeleteCommand(application));
        registerCommand(new CompleteCommand(application));
        registerCommand(new StopCommand(application));
    }

    @Override
    public void display() {
        System.out.println("Välkommen till din personliga finance app!");
        //System.out.println("Skriv 'help' för en lista på kommandon.");
    }
}
