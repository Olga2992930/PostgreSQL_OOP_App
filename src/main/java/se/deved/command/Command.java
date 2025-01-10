package se.deved.command;

import se.deved.Application;

public abstract class Command {

    private String name;
    private String description;
    protected Application application;

    public Command(String name, String description, Application application) {
        this.name = name;
        this.description = description;
        this.application = application;
    }

    public abstract void execute(String[] commandArgs);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
