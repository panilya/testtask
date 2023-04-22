package com.panilya.botscrewtesttask.service.commands;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.exception.CommandExecutionException;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.service.ChatCommand;
import com.panilya.botscrewtesttask.service.CommandInformation;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class CountOfEmployeesCommand implements ChatCommand {

    private static final CommandInformation commandInformation = CommandInformation.COUNT_OF_EMPLOYEE_FOR_THE_DEPARTMENT_COMMAND;

    private static final Predicate<String> satisfactionPredicate = userInput -> userInput.matches("Show count of employee for\\s(.+)");

    private DepartmentRepository departmentRepository;

    public CountOfEmployeesCommand(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String executeCommand(String commandContent) throws CommandExecutionException {
        final String departmentName = extractCommandParameter(commandContent);
        Optional<Department> department = departmentRepository.findByName(departmentName);
        if (department.isEmpty()) {
            throw new CommandExecutionException(String.format("Department %s not found", departmentName));
        }

        Long countOfEmployees = departmentRepository.countEmployeesInDepartment(departmentName);

        return commandInformation.getOutputTemplate().formatted(countOfEmployees);
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
