package dolla.parser;

import dolla.command.*;
import dolla.ui.LimitUi;

/**
 * This class handles all limit related parsing.
 */
public class LimitParser extends Parser {

    public LimitParser(String inputLine) {
        super(inputLine);
    }

    protected static final String LIMIT_COMMAND_LIST = "limits";
    protected static final String LIMIT_COMMAND_SET = "set";
    protected static final String LIMIT_COMMAND_REMOVE = "remove";

    protected static final String LIMIT_TYPE_S = "saving";
    protected static final String LIMIT_TYPE_B = "budget";

    protected static final String LIMIT_DURATION_D = "daily";
    protected static final String LIMIT_DURATION_W = "weekly";
    protected static final String LIMIT_DURATION_M = "monthly";

    @Override
    public Command handleInput(String mode, String inputLine) {
        if (commandToRun.equalsIgnoreCase(LIMIT_COMMAND_LIST)) { //show limit list todo:resolve bug
            return new ShowListCommand(mode);
        } else if (commandToRun.equalsIgnoreCase(LIMIT_COMMAND_SET)) { //add limit
            String limitType;
            double amount;
            String duration;
            int typeIndex = 1;
            int durationIndex = 3;
            try {
                limitType = typeFinder(typeIndex);
                amount = amountFinder();
                duration = durationFinder(durationIndex);
            } catch (IndexOutOfBoundsException e) {
                LimitUi.invalidSetCommandPrinter();
                return new ErrorCommand();
            } catch (NumberFormatException e) {
                LimitUi.invalidAmountPrinter();
                return new ErrorCommand();
            } catch (Exception e) {
                LimitUi.printErrorMsg();
                return new ErrorCommand();
            }
            return new AddLimitCommand(limitType, amount, duration);
        } else if (commandToRun.equalsIgnoreCase(LIMIT_COMMAND_REMOVE)) { //REMOVE DAILY BUDGET etc
            String limitType;
            String duration;
            int typeIndex = 2;
            int durationIndex = 1;
            try {
                //todo: check if limit exists before removing
                limitType = typeFinder(typeIndex);
                duration = durationFinder(durationIndex);
            } catch (IndexOutOfBoundsException e) {
                LimitUi.invalidRemoveCommandPrinter();
                return new ErrorCommand(); //todo: change to "limit does not exist" etc
            } catch (Exception e) {
                LimitUi.printErrorMsg();
                return new ErrorCommand();
            }
            return new RemoveLimitCommand(limitType, duration);
        } else if (commandToRun.equals("search")) {
            String component = inputArray[1];
            String content = inputArray[2];
            return new SearchCommand(mode, component, content);
        }
        return null;
    }

    /**
     * Method finds the type of limit being mentioned.
     * @return SAVING or BUDGET
     */
    private String typeFinder(int index) {
        String limitType = "";
        String inputType;
        inputType = inputArray[index];
        if (inputType.equalsIgnoreCase(LIMIT_TYPE_S)) {
            limitType = LIMIT_TYPE_S;
        } else if (inputType.equalsIgnoreCase(LIMIT_TYPE_B)) {
            limitType = LIMIT_TYPE_B;
        }
        return limitType;
    }

    private double amountFinder() {
        double amount = stringToDouble(inputArray[2]);
        return amount;
    }

    private String durationFinder(int index) {
        String limitDuration = "";
        String inputDuration;
        inputDuration = inputArray[index];
        if (inputDuration.equalsIgnoreCase((LIMIT_DURATION_D))) {
            limitDuration = LIMIT_DURATION_D;
        } else if (inputDuration.equalsIgnoreCase((LIMIT_DURATION_W))) {
            limitDuration = LIMIT_DURATION_W;
        } else if (inputDuration.equalsIgnoreCase((LIMIT_DURATION_M))) {
            limitDuration = LIMIT_DURATION_M;
        }
        return limitDuration;
    }
}
