package com.panilya.botscrewtesttask.service.commands;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.exception.CommandExecutionException;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.service.ChatCommand;
import com.panilya.botscrewtesttask.service.CommandInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CountOfEmployeesCommand implements ChatCommand {

    private static final CommandInformation commandInformation = CommandInformation.COUNT_OF_EMPLOYEE_FOR_THE_DEPARTMENT_COMMAND;

    private final DepartmentRepository departmentRepository;

    @Autowired
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
        if (countOfEmployees == null) {
            throw new CommandExecutionException(String.format("Count of employees for department %s not found", departmentName));
        }

        return commandInformation.getOutputTemplate().formatted(countOfEmployees);
    }

    @Override
    public CommandInformation getCommandInformation() {
        return commandInformation;
    }

    private String extractCommandParameter(String commandContent) {
        return commandContent.substring(commandInformation.getInputTemplate().length() - 2);
    }

}
