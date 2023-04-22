package com.panilya.botscrewtesttask.service.commands;

import com.panilya.botscrewtesttask.exception.CommandExecutionException;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.repository.LecturerRepository;
import com.panilya.botscrewtesttask.service.ChatCommand;
import com.panilya.botscrewtesttask.service.CommandInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GlobalSearchCommand implements ChatCommand {

    private static final CommandInformation commandInformation = CommandInformation.GLOBAL_SEARCH_COMMAND;

    private final LecturerRepository lecturerRepository;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public GlobalSearchCommand(LecturerRepository lecturerRepository, DepartmentRepository departmentRepository) {
        this.lecturerRepository = lecturerRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String executeCommand(String commandContent) throws CommandExecutionException {
        final String inputParameter = extractCommandParameter(commandContent);

        List<String> searchResult = lecturerRepository.globalSearch(inputParameter);
        if (searchResult.isEmpty()) {
            throw new CommandExecutionException(String.format("Search result for department %s not found", inputParameter));
        }

        return commandInformation.getOutputTemplate().formatted(String.join(", ", searchResult));
    }

    @Override
    public CommandInformation getCommandInformation() {
        return commandInformation;
    }

    private String extractCommandParameter(String commandContent) {
        return commandContent.substring(commandInformation.getInputTemplate().length() - 2);
    }

}
