package se.deved.command;

import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.*;

import se.deved.Application;
import se.deved.Transaction;
import se.deved.TransactionsManager;
import se.deved.utility.StringToDate;

public class CreateCommand extends Command {

    public CreateCommand(Application application) {
        super("create", "skapa en ny transaktion", application);
    }

    @Override
    public void execute(String[] commandArgs) {


        TransactionsManager manager = new TransactionsManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Beskriv transaktionen: ");
        String beskrivning = scanner.nextLine();
        System.out.println("Ange belopp för transaktionen. ");
        System.out.print("Skriv `-` om det är utgift, skriv `+` om det är inkomst: ");
        int belopp = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ange datum (yyyy-mm-dd): ");
        String dateString = scanner.nextLine();
        //Date datum = StringToDate.parseString(dateString);
        //if (datum == null) {
            //System.out.println("Fel datumformat. Försök igen.");
            //return;
        //}
        Date datum;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            java.util.Date javaDate = dateFormat.parse(dateString);
            datum = new Date(javaDate.getTime());
        } catch (ParseException exception) {
            System.out.println("Could not parse date. Did you enter the correct format?");
            return;
        }




                try (PreparedStatement insertTransaction = Application.conn.prepareStatement("INSERT INTO transactionstable (beskrivning, belopp, datum) VALUES (?, ?, ?)")) {

        insertTransaction.setString(1, beskrivning);
        insertTransaction.setInt(2, belopp);
        insertTransaction.setDate(3, datum);

            if (insertTransaction.executeUpdate() == 0) {
                System.out.println("Nothing was inserted, perhaps there was a conflict?");
                return;
            }
        } catch (SQLException exception) {
            System.out.println("Failed to save to database.");
            return;
        }







        //Transaction transaction = new Transaction(beskrivning, belopp, datum);
        

        //manager.läggTillTransaction(beskrivning, belopp, datum);
        //System.out.println("______________________________________________________________");
        //System.out.println("Kör vidare! ('create'/'balance'/'delete'/'expenses'/'income'/'help'/'stop')");
        //try {
            //application.getTransactionManager().save(transaction);
            //System.out.println("Sparade transaction '" + transaction.beskrivning + "'.");
        //} catch (Exception exception) {
            //exception.printStackTrace();
            //System.out.println("Kunde inte spara transaction. Försök igen.");
        //}
    }
}
