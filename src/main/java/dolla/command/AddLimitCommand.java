package dolla.command;

import dolla.DollaData;
import dolla.task.Limit;
import dolla.ui.LimitUi;

/**
 * AddLimitCommand is used to create a new Limit entity.
 */
public class AddLimitCommand extends Command {

    private String type;
    private double amount;
    private String duration;

    /**
     * Instantiates a new AddLimitCommand.
     * @param type type of limit
     * @param amount amount of limit
     * @param duration duration of limit
     */
    public AddLimitCommand(String type, double amount, String duration) {
        this.type = type;
        this.amount = amount;
        this.duration = duration;
    }

    @Override
    public void execute(DollaData dollaData) {
        Limit newLimit = new Limit(type, amount, duration);
        dollaData.addToRecordList("limit", newLimit);
        //todo: need to add budget and show and deduct money every time there is an expense entry
        LimitUi.echoAddRecord(newLimit);
    }

    @Override
    public String getCommandInfo() {
        return null;
    };
}