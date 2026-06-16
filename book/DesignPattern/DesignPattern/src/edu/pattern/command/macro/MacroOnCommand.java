package edu.pattern.command.macro;

import edu.pattern.command.Command;

public class MacroOnCommand implements Command {
    Command[] commands;

    public MacroOnCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (Command command : commands) {
            command.undo();
        }
    }
}
