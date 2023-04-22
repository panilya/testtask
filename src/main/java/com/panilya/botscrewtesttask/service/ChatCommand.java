package com.panilya.botscrewtesttask.service;

import com.panilya.botscrewtesttask.exception.CommandExecutionException;
import org.springframework.stereotype.Component;

@Component
public interface ChatCommand {

    String executeCommand(String commandContent) throws CommandExecutionException;

    CommandInformation getCommandInformation();

}
