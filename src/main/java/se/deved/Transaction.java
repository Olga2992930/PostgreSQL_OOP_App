package se.deved;

import java.util.Date;

public class Transaction {

    public String beskrivning;
    public double belopp;
    public Date datum;

    public Transaction(String beskrivning, double belopp, Date datum) {
        this.beskrivning = beskrivning;
        this.belopp = belopp;
        this.datum = datum;
    }

    public double getBelopp() {
        return belopp;
    }

    public void visaTransaction() {
        System.out.println("Transaktion: " + beskrivning + " | Belopp: " + belopp + " SEK | Datum: " + datum);
    }

    
}