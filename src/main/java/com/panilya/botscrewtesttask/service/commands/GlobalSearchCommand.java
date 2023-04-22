package com.panilya.botscrewtesttask.service.commands;

import com.panilya.botscrewtesttask.exception.CommandExecutionException;
import com.panilya.botscrewtesttask.repository.LecturerRepository;
import com.panilya.botscrewtesttask.service.ChatCommand;
import com.panilya.botscrewtesttask.service.CommandInformation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class GlobalSearchCommand implements ChatCommand {

    private static final CommandInformation commandInformation = CommandInformation.GLOBAL_SEARCH_COMMAND;

    private static final Predicate<String> satisfactionPredicate = userInput -> userInput.matches("Global search by\\s(.+)");

    private LecturerRepository lecturerRepository;

    public GlobalSearchCommand(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public String executeCommand(String commandContent) throws CommandExecutionException {
        final String inputParameter = extractCommandParameter(commandContent);

        List<String> searchResult = lecturerRepository.globalSearch(inputParameter);
        if (searchResult.isEmpty()) {
            return commandInformation.getOutputTemplate().formatted("No results found");
        }

        return commandInformation.getOutputTemplate().formatted(String.join(", ", searchResult));
    }

    @Override
    public CommandInformation getCommandInformation() {
        return commandInformation;
    }

    @Override
    public Predicate<String> getSatisfactionPredicate() {
        return satisfactionPredicate;
    }

    private String extractCommandParameter(String commandContent) {
        return commandContent.substring(commandInformation.getInputTemplate().length() - 2);
    }

}
