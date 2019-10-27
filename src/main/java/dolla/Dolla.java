package dolla;

import dolla.command.Command;
import dolla.parser.MainParser;
import static dolla.Storage.load;

import java.util.Scanner;

/**
 * <h1>duke.Dolla</h1>
 * duke.Dolla is a chat-bot styled todo_list manager.
 *
 * @author  Aik Peng
 * @version 1.0
 * @since   2019-07-26
 */
public class Dolla {

    private DollaData dollaData = new DollaData();

    /**
     * Creates an instance of Dolla using a data loaded from /data/dolla.txt
     */
    public Dolla() {
        load(); //load from save
    }

    /**
     * Runs the main program of duke.Dolla
     * @throws Exception when exceptional condition happens
     */
    public void run() throws Exception {
        boolean isExit = false;
        Reminder reminder = new Reminder("debt");
        reminder.showReminder(dollaData);
        Scanner input = new Scanner(System.in); // TODO: Add to Ui or MainParser instead?
        while (isExit == false) {
            if (input.hasNextLine()) {
                String inputLine = input.nextLine();
                if (inputLine.equals("bye")) {
                    isExit = true;
                    MainParser.exit();
                } else {
                    Command c = MainParser.handleInput(dollaData.getMode(), inputLine);
                    c.execute(dollaData);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception { // Exception needs to be handled?
        new Dolla().run();
    }
}
