package dolla.command;

import dolla.DollaData;
import dolla.task.RecordList;
import dolla.ui.DebtUi;
import dolla.ui.ListUi;
import dolla.ui.Ui;

import java.util.ArrayList;

//@@author tatayu
/**
 * This class shows the bill list
 */
public class ShowBillListCommand extends Command {

    private String mode;
    public ShowBillListCommand(String mode) {
        this.mode = mode;
    }

    @Override
    public void execute(DollaData dollaData) throws Exception {
        RecordList recordList = new RecordList(new ArrayList<>());
        recordList = dollaData.getBillRecordList();

        boolean listIsEmpty = (recordList.size() == 0);

        if (listIsEmpty) { // TODO: Place this in proper place
            ListUi.printEmptyListError(mode);
            return;
        } else {
            DebtUi.printBillList(recordList);
        }
    }

    @Override
    public String getCommandInfo() {
        return null;
    }
}
