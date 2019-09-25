package duke.task;

import duke.Time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The type Task that deadline, todo and event inherits.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected char type;
    protected LocalDateTime date = Time.readDateTime("01/01/0001 0000"); // Default date

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets isdone.
     *
     * @return true if task is done, else false
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X"); // returns O or X
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets date str.
     *
     * @return the date str
     */
    public String getDateStr() {
        return "";
    }

    /**
     * Gets date.
     *
     * @return the date in localdatetime format
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Converts time to string.
     *
     * @param time the time
     * @return the string
     */
    public String timeToString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH);
        String timeStr = time.format(formatter);
        return timeStr;
    }

    /**
     * Get task in text format.
     *
     * @return the task
     */
    public String getTask() {
        return "[" + type + "][" + getStatusIcon() + "] " + description + getDateStr();
    }

    /**
     * Format date save string.
     *
     * @return the string
     */
    public String formatDateSave() {
        return "";
    }

    /**
     * Formats the string into the format to be saved as.
     *
     * @return the string
     */
    public String formatSave() {
        return type + " | " + (isDone ? 1 : 0) + " | " + description + formatDateSave();
    }

    /**
     * Gets task type.
     *
     * @return the task type
     */
    public char getTaskType() { return type; }

    /**
     * Gets task description.
     *
     * @return the task description
     */
    public String getTaskDescription() { return description; }
}
