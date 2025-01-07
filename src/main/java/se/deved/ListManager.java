package se.deved;

import java.util.ArrayList;
import java.util.List;

public class ListManager implements SaveManager {
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void remove(Transaction transaction) {
        transactions.remove(transaction);
    }

    @Override
    public Transaction getByTitle(String title) {
        for (Transaction transaction : transactions) {
            if (transaction.beskrivning.equalsIgnoreCase(title)) {
                return transaction;
            }
        }
        return null;
    }
}
