package yappingbot.commands.commands;

import yappingbot.commands.CreateTaskCommandBase;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Task;
import yappingbot.tasks.Todo;

/**
 * Creates to-do task.
 */
public class CreateTodoCommand extends CreateTaskCommandBase<CreateTodoCommand.Args> {

    protected enum Args implements ArgEnums<Args> {
        TASK_NAME("", true);

        private final String keyword;
        private final boolean isRequired;

        Args(String keyword, boolean isRequired) {
            this.keyword = keyword;
            this.isRequired = isRequired;
        }
        @Override
        public String getKeyword() {
            return keyword;
        }
        @Override
        public boolean isRequired() {
            return isRequired;
        }
    }

    /**
     * Subclasses must override this, linking it to an Enum of possible Argument Types. Usually
     * <i>return A.class</i>.
     *
     * @return The Class of th Enums defined in the subclass holding the possible Argument Types.
     */
    @Override
    protected Class<Args> getArgumentClass() {
        return Args.class;
    }

    /**
     * Subclasses must override this, linking it to the value in the Enum referring to the first
     * Argument (unmarked argument).
     *
     * @return The Enum defined in the subclass holding the possible Argument Types.
     */
    @Override
    protected Args getFirstArgumentType() {
        return Args.TASK_NAME;
    }

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @param argSlices ordered array of strings with argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown argument
     *                                             flag given.
     */
    public CreateTodoCommand(String[] argSlices) throws YappingBotIncorrectCommandException {
        super(argSlices);
    }

    /**
     * Returns the usage help text, helpful for guiding user on correct usage.
     *
     * @return String of help text.
     */
    @Override
    public String getHelpText() {
        return ReplyTextMessages.TODO_USAGE;
    }

    /**
     * Creates the task based on each subclass's implementation of its Task.
     *
     * @return Task that is newly created
     */
    @Override
    protected Task createNewTask() {
        assert arguments.containsKey(Args.TASK_NAME);
        return new Todo(String.join("", arguments.get(Args.TASK_NAME)), false);
    }
}
