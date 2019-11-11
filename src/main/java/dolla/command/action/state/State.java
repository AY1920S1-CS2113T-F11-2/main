package dolla.command.action.state;

import dolla.model.Record;

import java.util.ArrayList;

//@@author yetong1895
public class State {
    protected ArrayList<Record> entryState = new ArrayList<>();
    protected ArrayList<Record> debtState = new ArrayList<>();
    protected ArrayList<Record> limitState = new ArrayList<>();
    protected ArrayList<Record> shortcutState = new ArrayList<>();
    protected String mode;

    public ArrayList<Record> getShortcutState() {
        return shortcutState;
    }

    public ArrayList<Record> getLimitState() {
        return limitState;
    }

    public ArrayList<Record> getEntryState() {
        return entryState;
    }

    public ArrayList<Record> getDebtState() {
        return debtState;
    }
}
