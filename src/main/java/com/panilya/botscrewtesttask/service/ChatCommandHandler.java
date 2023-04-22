package com.panilya.botscrewtesttask.service;

import com.panilya.botscrewtesttask.exception.CommandNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ChatCommandHandler implements CommandHandler {

    private CommandFactory commandFactory;

    public ChatCommandHandler(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public String handleCommand(String commandContent) throws CommandNotFoundException {
        return commandFactory.getCommand(commandContent).executeCommand(commandContent);
    }

}
