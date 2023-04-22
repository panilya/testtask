package com.panilya.botscrewtesttask.service.commands;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.database.Lecturer;
import com.panilya.botscrewtesttask.exception.CommandExecutionException;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.service.ChatCommand;
import com.panilya.botscrewtesttask.service.CommandInformation;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class HeadOfDepartmentCommand implements ChatCommand {

    private static final CommandInformation commandInformation = CommandInformation.HEAD_OF_DEPARTMENT_COMMAND;

    private static final Predicate<String> satisfactionPredicate = userInput -> userInput.matches("Who is head of department\\s(.+)");

    private DepartmentRepository departmentRepository;

    public HeadOfDepartmentCommand(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String executeCommand(String commandContent) {
        final String departmentName = extractCommandParameter(commandContent);
        Optional<Department> department = departmentRepository.findByName(departmentName);
        if (department.isEmpty()) {
            throw new CommandExecutionException(String.format("Department %s not found", departmentName));
        }

        Optional<Lecturer> headOfDepartment = departmentRepository.findHeadOfDepartment(department.get().getName());
        if (headOfDepartment.isEmpty()) {
            throw new CommandExecutionException(String.format("Department %s not found", departmentName));
        }

        return commandInformation.getOutputTemplate().formatted(departmentName, headOfDepartment.get().getName());
    }

    @Override
    public CommandInformation getCommandInformation() {
        return commandInformation;
    }

    @Override
    public Predicate<String> getSatisfactionPredicate() {
        return satisfactionPredicate;
    }

    public String extractCommandParameter(String commandContent) {
        return commandContent.substring(commandInformation.getInputTemplate().length() - 2);

    }

}
