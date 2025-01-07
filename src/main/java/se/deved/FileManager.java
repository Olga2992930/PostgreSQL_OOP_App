package se.deved;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

//import javax.swing.plaf.basic.BasicSliderUI;

public class FileManager implements SaveManager {
    private List<Transaction> transactions = new ArrayList<>();
      
    @Override
    public void save(Transaction transaction) {
        File folder = new File("./transactions");
        folder.mkdirs();

        File file = new File(folder, transaction.beskrivning);
        try {
            file.createNewFile();

            // Serialization
            FileWriter writer = new FileWriter(file);

            // Vi sparar all information 
            writer.append(transaction.beskrivning)
                    .append("\n")
                    .append(String.valueOf(transaction.belopp))
                    .append("\n")
                    .append(String.valueOf(transaction.datum))
                    .append("\n");

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void remove(Transaction transaction) {
        File folder = new File("./transactions");
        if (!folder.exists())
            return;

        File file = new File(folder, transaction.beskrivning);
        if (!folder.exists())
            return;

        file.delete();
    }

    @Override
    public Transaction getByTitle(String title) {
        File folder = new File("./transactions");
        if (!folder.exists())
            return null;

        File file = new File(folder, title);
        if (!folder.exists())
            return null;

        // Deserialization
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffered = new BufferedReader(reader);
            for (Transaction transaction : transactions) {
                if (transaction.beskrivning.equalsIgnoreCase(title)) {
                    return transaction;
                }
            }
            buffered.close();
            return null;
          

        } catch (Exception e) {
            return null;
        }
    }

}
