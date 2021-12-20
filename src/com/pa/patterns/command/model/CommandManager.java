package com.pa.patterns.command.model;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> commands;

    public CommandManager() {
        commands = new Stack();
    }

    public void executeCommand(Command command){
        command.execute();
        commands.push(command);
    }

    public void undo(){
        if (commands.empty())
            throw new ShoppingCartException("No Undo");
        Command cmd = commands.pop();
        cmd.unExecute();
    }
}
