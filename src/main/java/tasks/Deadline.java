package tasks;

import exceptions.YappingBotIncorrectCommandException;
import exceptions.YappingBotInvalidSaveFileException;
import stringconstants.ReplyTextMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES;

public class Deadline extends Task {
    public Deadline() {
        this("",false);
    }

    public String getDeadline() {
        return deadline.toString();
    }

    public void setDeadline(String deadline) throws YappingBotIncorrectCommandException {
        try {
            this.deadline = LocalDateTime.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new YappingBotIncorrectCommandException(ReplyTextMessages.TIME_PARSE_HINT, e.getMessage());
        }
    }

    private LocalDateTime deadline;

    public Deadline(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.deadline = LocalDateTime.now();
    }
    public Deadline(String taskName, boolean taskDone, String deadline) throws YappingBotIncorrectCommandException {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.setDeadline(deadline);
    }

    @Override
    public String getTaskTypeSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.getTaskName(), this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String serialize() {
        return String.format("%s:%s",
                super.serialize(),
                this.getDeadline().replaceAll(":", "/colon")
        );
    }
    @Override
    public void deserialize(String[] sString) throws YappingBotInvalidSaveFileException {
        if (sString.length < 4) {
            throw new YappingBotInvalidSaveFileException(INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES);
        }
        try {
            super.deserialize(sString);
            this.setDeadline(sString[3].replaceAll("/colon", ":"));
        } catch (IllegalArgumentException e) {
            throw new YappingBotInvalidSaveFileException(e.getMessage());
        }
    }
}

