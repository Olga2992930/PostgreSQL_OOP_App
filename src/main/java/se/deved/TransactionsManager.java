package se.deved;

import java.util.ArrayList;
import java.util.Date;

public class TransactionsManager {
    private static ArrayList<Transaction> transactioner = new ArrayList<>();

    public void läggTillTransaction(String beskrivning, double belopp, Date datum) {
        Transaction transaction = new Transaction(beskrivning, belopp, datum);
        transactioner.add(transaction);
        System.out.println("Du är klar med transaktionen.");
    }

    public void visaAllaTransactioner() {
        if (transactioner.isEmpty()) {
            System.out.println("Transaktionslistan är tom.");
        } else {
            System.out.println("Alla transaktioner:");
            for (int i = 0; i < transactioner.size(); i++) {
                System.out.print((i + 1) + ". ");
                transactioner.get(i).visaTransaction();
            }
        }
    }

    public double beräknaSaldo() {
        double saldo = 0;
        for (Transaction transaktion : transactioner) {
            saldo += transaktion.getBelopp();
        }
        return saldo;
    }

    public void visaSaldo() {
        double saldo = beräknaSaldo();
        System.out.println("Nuvarande saldo på kontot är: " + saldo + " kr");
    }

    public static ArrayList<Transaction> getTransactioner() {
        return transactioner;
    }    
}
