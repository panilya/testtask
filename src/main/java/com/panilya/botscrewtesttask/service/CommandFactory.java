package com.panilya.botscrewtesttask.service;

import com.panilya.botscrewtesttask.exception.CommandNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public final class CommandFactory {

    private static final Map<CommandInformation, ChatCommand> commandCache = new HashMap<>();

    private List<ChatCommand> commands;

    private CommandFactory(List<ChatCommand> commands) {
        this.commands = commands;
        for (ChatCommand command : commands) {
            commandCache.put(command.getCommandInformation(), command);
        }
    }

    public ChatCommand getCommand(String userInput) throws CommandNotFoundException {
        for (ChatCommand command : commands) {
            if (command.getSatisfactionPredicate().test(userInput)) {
                return commandCache.get(command.getCommandInformation());
            }
        }
        throw new CommandNotFoundException("Command not found");
    }

}
