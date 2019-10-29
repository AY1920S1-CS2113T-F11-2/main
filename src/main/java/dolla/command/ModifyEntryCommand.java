package dolla.command;

import dolla.DollaData;
import dolla.ui.Ui;
import dolla.task.Entry;

import java.time.LocalDate;

public class ModifyEntryCommand extends Command {

    private String type;
    private double amount;
    private String description;
    private LocalDate date;

    /**
     * Instantiates a new ModifyEntryCommand.
     * @param type type of modification
     * @param amount amount to modify
     * @param description description
     * @param date date
     */
    public ModifyEntryCommand(String type, double amount, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(DollaData dollaData) {
        Entry newEntry = new Entry(type, amount, description, date);
        dollaData.modifyRecordList(newEntry);
        Ui.echoAddRecord(newEntry);
        dollaData.updateMode("entry");
    }

    @Override
    public String getCommandInfo() {
        return null;
    };
}
