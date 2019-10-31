package dolla.command;

import dolla.DollaData;
import dolla.parser.Parser;
import dolla.ui.Ui;

/**
 * Command is an abstract class which all types of command will inherit from.
 */
public abstract class Command {
    protected int index;

    /**
     * This method is called execute the titular command after every user input.
     * @throws Exception handle exception
     */
    public abstract void execute(DollaData dollaData) throws Exception;

    /**
     * Returns a string containing the data stored in the command.
     * @return string containing data stored in command.
     */
    public abstract String getCommandInfo();

    /**
     * Returns an integer variable from the specified string.
     * <p>
     *     Returns 0 if the specified string is not of a number.
     * </p>
     * <p>
     *     Mainly used for using the specified string for calculations in the command.
     *     IE. Accessing arrays.
     * </p>
     * @param str String (of number) to be converted into integer type.
     * @return Integer type of the specified string.
     */
    public int stringToInt(String str) {
        int newInt = 0;
        try {
            newInt = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            Ui.printInvalidNumberError(str);
        }
        return newInt;
    }

    /**
     * This method will call the resetUndoFlag method in the respective mode.
     */
    protected void resetUndoFlag() {
        Parser.resetUndoFlag();
    }

    /**
     * This method will call the resetRedoFlag method in the respective mode.
     */
    protected void resetRedoFlag() {
        Parser.resetRedoFlag();
    }

    protected Boolean recordDoesNotExist(int recordIndex) {
        return (recordIndex == - 1);
    }
}
