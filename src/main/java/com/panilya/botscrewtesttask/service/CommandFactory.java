package com.panilya.botscrewtesttask.service;

import com.panilya.botscrewtesttask.exception.CommandNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public final class CommandFactory {

    private static final Map<CommandInformation, ChatCommand> commandCache = new HashMap<>();

    @Autowired
    private CommandFactory(List<ChatCommand> commands) {
        for (ChatCommand command : commands) {
            commandCache.put(command.getCommandInformation(), command);
        }
    }

    public ChatCommand getCommand(String userInput) throws CommandNotFoundException {
        CommandInformation commandInformation = decideChatCommand(userInput);
        if (commandInformation == null) {
            throw new CommandNotFoundException("Command not found. Check your message, please.");
        }
        return commandCache.get(commandInformation);
    }

    // TODO: rework. Make it more flexible (less static).
    private CommandInformation decideChatCommand(String userInput) {
        if (CommandInformation.HEAD_OF_DEPARTMENT_COMMAND.getSatisfactionPredicate().test(userInput)) {
            return CommandInformation.HEAD_OF_DEPARTMENT_COMMAND;
        } else if (CommandInformation.SHOW_STATISTICS_COMMAND.getSatisfactionPredicate().test(userInput)) {
            return CommandInformation.SHOW_STATISTICS_COMMAND;
        } else if (CommandInformation.SHOW_AVERAGE_SALARY_FOR_THE_DEPARTMENT_COMMAND.getSatisfactionPredicate().test(userInput)) {
            return CommandInformation.SHOW_AVERAGE_SALARY_FOR_THE_DEPARTMENT_COMMAND;
        } else if (CommandInformation.COUNT_OF_EMPLOYEE_FOR_THE_DEPARTMENT_COMMAND.getSatisfactionPredicate().test(userInput)) {
            return CommandInformation.COUNT_OF_EMPLOYEE_FOR_THE_DEPARTMENT_COMMAND;
        } else if (CommandInformation.GLOBAL_SEARCH_COMMAND.getSatisfactionPredicate().test(userInput)) {
            return CommandInformation.GLOBAL_SEARCH_COMMAND;
        }
        return null;
    }

}
