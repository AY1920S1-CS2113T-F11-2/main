package dolla.parser;

import dolla.Time;
import dolla.Ui;
import dolla.command.AddDeadlineCommand;
import dolla.command.AddEventCommand;
import dolla.command.Command;
import dolla.command.ErrorCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public abstract class Parser {

    protected LocalDate date;
    protected String description;
    protected String inputLine;
    protected String[] inputArray;
    protected String commandToRun;
    protected static final String SPACE = " ";
    protected static int undoFlag = 0;
    protected static int redoFlag = 0;
    protected static int prevPosition;


    /**
     * Instantiates a new parser.
     * @param inputLine inputLine
     */
    public Parser(String inputLine) {
        this.inputLine = inputLine;
        this.inputArray = inputLine.split(SPACE);
        this.commandToRun = inputArray[0];
    }

    public abstract Command handleInput();

    /**
     * Splits the input from the user and assigns the relevant data into description and date variables.
     * If the incorrect format is given in the input, the corresponding alert will be printed.
     */
    public void extractDescTime() throws Exception {
        // dataArray[0] is command, amount and description, dataArray[1] is time and tag
        String[] dataArray = inputLine.split(" /on ");
        String dateString = (dataArray[1].split("/tag"))[0];
        description = dataArray[0].split(inputArray[2] + " ")[1];
        try {
            date = Time.readDate(dateString);
        } catch (ArrayIndexOutOfBoundsException e) {
            // TODO: Shouldn't happen anymore, need to test if this will happen still
            Ui.printMsg("Please add '/at <date>' after your task to specify the entry date.");
            throw new Exception("missing date");
        }  catch (DateTimeParseException e) {
            Ui.printDateFormatError();
            throw new Exception("invalid date");
        }
    }

    /**
     * Returns a double variable from the specified string.
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
    public double stringToDouble(String str) {
        double newDouble = 0.0;
        try {
            newDouble = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            Ui.printInvalidNumberError(str);
        }
        return newDouble;
    }

    // TODO: Update
    public Command invalidCommand() {
        Ui.printInvalidCommandError();
        return new ErrorCommand();
    }

    /**
     * Checks if the first word after 'add' is either 'income' or 'expense'.
     * @param s String to be analysed.
     * @return Either 'expense' or 'income' if either are passed in.
     * @throws Exception ???
     */
    public static String verifyAddType(String s) throws Exception {
        if (s.equals("income") || s.equals("expense")) {
            return s;
        } else {
            Ui.printInvalidEntryType();
            throw new Exception("invalid type");
        }
    }

    /**
     * Returns true if no error occurs while creating the required variables for 'addEntryCommand'.
     * Also splits description and time components in the process.
     * @return true if no error occurs.
     */
    public boolean verifyAddCommand() {
        try {
            verifyAddType(inputArray[1]);
            stringToDouble(inputArray[2]);
            extractDescTime();
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidEntryFormatError();
            return false;
        } catch (Exception e) {
            return false; // If error occurs, stop the method!
        }
        return true;
    }

    /**
     * Verifies modify command. //todo: edit javadoc
     * @return
     */
    public boolean verifyModifyCommand() {
        try {
            Integer.parseInt(inputArray[1]);
            // TODO: Add support for modifying specific categories
        } catch (Exception e) {
            Ui.printInvalidModifyFormatError();
            return false;
        }
        return true;
    }

    /**
     * This method will set the prevPosition int in this class.
     * @param prevPosition the prevPosition of a deleted input.
     */
    public static void setPrevPosition(int prevPosition) {
        Parser.prevPosition = prevPosition;
        undoFlag = 1;
    }

    /**
     * THis method will set prevPosition to -1 and undoFlag to 0.
     */
    public static void resetUndoFlag() {
        Parser.prevPosition = -1;
        undoFlag = 0;
    }

    /**
     * This method will set redoFlag to 1.
     */
    public static void setRedoFlag() {
        redoFlag = 1;
    }

    /**
     * This method will set redoFlag to 0.
     */
    public static void resetRedoFlag() {
        redoFlag = 0;
    }
}
