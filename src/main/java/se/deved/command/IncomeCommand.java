package se.deved.command;

import se.deved.Application;
import se.deved.Transaction;
import se.deved.TransactionsManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class IncomeCommand extends Command {
    public IncomeCommand(Application application) {
        super("income", "visa inkomster", application);
    }

    @Override
    public void execute(String[] commandArgs) {
        Scanner scanner = new Scanner(System.in);
        TransactionsManager manager = new TransactionsManager();
        double totalInkomster = 0;
        int year = 0;
        int month = 0;
        Calendar idag = Calendar.getInstance();
        Date endDate;
        Date startDate;
        Date dateIdag;
        System.out.println("Välj tidsperiod:");
        System.out.println("1. Årsvis");
        System.out.println("2. Månadsvis");
        System.out.println("3. Veckovis");
        System.out.println("4. Dagvis");
        System.out.print("Ange ditt val: ");
        int period = scanner.nextInt();
        switch (period) {
            case 1:
                totalInkomster = 0;
                year = idag.get(Calendar.YEAR);
                idag.set(Calendar.YEAR, year - 1);
                startDate = idag.getTime();
                                idag.set(Calendar.YEAR, year);
                endDate = idag.getTime();
                              for (Transaction transaction : manager.getTransactioner()) {
                    if (transaction.belopp > 0 && transaction.datum.after(startDate)
                            && transaction.datum.before(endDate)) {
                        // System.out.println("Transaktion: " + transaction.beskrivning + " | Belopp: "
                        //         + transaction.belopp + " SEK | Datum: " + transaction.datum);
                        totalInkomster += transaction.belopp;
                    }
                }
                break;
            case 2:
                totalInkomster = 0;
                year = idag.get(Calendar.YEAR);
                month = idag.get(Calendar.MONTH);
                idag.set(Calendar.MONTH, month - 1);
                startDate = idag.getTime();
                idag.set(Calendar.MONTH, month);
                endDate = idag.getTime();
                for (Transaction transaction : manager.getTransactioner()) {
                    if (transaction.belopp > 0 && transaction.datum.after(startDate)
                            && transaction.datum.before(endDate)) {
                        totalInkomster += transaction.belopp;
                    }
                }
                break;
            case 3:
                totalInkomster = 0;
                dateIdag = idag.getTime();
                idag.setTime(dateIdag);
                idag.set(Calendar.DAY_OF_WEEK, idag.getFirstDayOfWeek());
                startDate = idag.getTime();
                idag.add(idag.WEEK_OF_YEAR, 1);
                endDate = idag.getTime();
                for (Transaction transaction : manager.getTransactioner()) {
                    if (transaction.belopp > 0 && transaction.datum.after(startDate)
                            && transaction.datum.before(endDate)) {
                        totalInkomster += transaction.belopp;
                    }
                }
                break;
            case 4:
                totalInkomster = 0;
                dateIdag = idag.getTime();
                idag.setTime(dateIdag);
                idag.set(Calendar.HOUR_OF_DAY, 0);
                idag.set(Calendar.MINUTE, 0);
                idag.set(Calendar.SECOND, 0);
                startDate = idag.getTime();
                idag.add(Calendar.DAY_OF_YEAR, 1);
                endDate = idag.getTime();

                               for (Transaction transaction : manager.getTransactioner()) {
                    if (transaction.belopp < 0 && transaction.datum.after(startDate)
                            && transaction.datum.before(endDate)) {
                        totalInkomster += transaction.belopp;
                    }
                }
                break;
            }
            System.out.println("Inkomster: " + totalInkomster + " SEK");
    }
}
