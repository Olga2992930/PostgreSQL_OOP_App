package se.deved.command;

import se.deved.Application;

public class StopCommand extends Command {

    public StopCommand(Application application) {
        super("stop", "avsluta appen", application);
    }

    @Override
    public void execute(String[] commandArgs) {
        application.stop();
        System.out.println("Applikationen avslutas...");
    }
}