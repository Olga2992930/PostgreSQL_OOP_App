package se.deved;

public interface SaveManager {
    void save(Transaction transaction);
    void remove(Transaction transaction);
    Transaction getByTitle(String beskrivning);
}
