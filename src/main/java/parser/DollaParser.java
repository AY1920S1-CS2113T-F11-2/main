package parser;

import dolla.Ui;
import dolla.command.AddEntryCommand;
import dolla.command.Command;
import dolla.command.ErrorCommand;

public class DollaParser extends Parser {

    public DollaParser(String inputLine) {
        super(inputLine);
    }

    @Override
    public Command handleInput(String inputLine) {
        String[] inputArray = inputLine.split(" ");
        String commandToRun = inputArray[0];

        if (commandToRun.equals("add")) {
            String entryType = null;
            double amount = 0.0;
            try {
                entryType = verifyType(inputArray[1]);
                amount = stringToDouble(inputArray[2]);
                splitDescTime();
            } catch (IndexOutOfBoundsException e) {
                Ui.printInvalidEntryFormatError();
            } catch (Exception e) {
                return new ErrorCommand(); // If error occurs, stop the method!
            }

            return new AddEntryCommand(entryType, amount, description, date);

//            switch(commandToRun) {
//                case "income":
//                case "expense":
//                    return new AddExpenseCommand();
//                default:
//                    return new ErrorCommand();
//            }

        }

        return null;
    }

    private String verifyType(String s) throws Exception {
        if (s.equals("income") || s.equals("expense")) {
            return s;
        } else {
            Ui.printInvalidEntryType();
            throw new Exception("invalid type");
        }
    }
}