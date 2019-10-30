package dolla.storage;

import dolla.task.Record;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorageWrite extends Storage {

    /**
     * This method will set the ArrayList of entries in this class.
     * @param entries the ArrayList this method going to set to.
     */
    public static void setEntries(ArrayList<Record> entries) {
        Storage.entries = entries;
        save();
    }

    /**
     * This method will set the ArrayList of limits in this class.
     * @param limits the ArrayList this method going to set to.
     */
    public static void setLimits(ArrayList<Record> limits) {
        Storage.limits = limits;
        save();
    }

    /**
     * This method will set the ArrayList of debts in this class.
     * @param debts the ArrayList this method going to set to.
     */
    public static void setDebts(ArrayList<Record> debts) {
        Storage.debts = debts;
        save();
    }

    /**
     * This method will set the ArrayList of debts in this class.
     * @param shortcuts the ArrayList this method going to set to.
     */
    public static void setShortcuts(ArrayList<Record> shortcuts) {
        Storage.shortcuts = shortcuts;
        save();
    }

    /**
     * This method will save all the ArrayList into an external text file.
     */
    protected static void save() {
        try (FileWriter file = new FileWriter("./data/dolla.txt")) {
            storage.addAll(entries);
            storage.addAll(debts);
            storage.addAll(limits);
            storage.addAll(shortcuts);

            for (Record currSave : storage) {
                String fileContent = currSave.formatSave();
                file.write(fileContent);
                file.write(System.lineSeparator());
            }
            storage.clear();

        } catch (IOException e) {
            System.out.println("***Error writing to dolla.txt***");
        }
    }
}