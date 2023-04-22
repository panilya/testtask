package com.panilya.botscrewtesttask.service.commands;

import com.panilya.botscrewtesttask.database.Department;
import com.panilya.botscrewtesttask.exception.CommandExecutionException;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.service.ChatCommand;
import com.panilya.botscrewtesttask.service.CommandInformation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class AverageSalaryForTheDepartmentCommand implements ChatCommand {

    private static final CommandInformation commandInformation = CommandInformation.SHOW_AVERAGE_SALARY_FOR_THE_DEPARTMENT_COMMAND;

    private static final Predicate<String> satisfactionPredicate = userInput -> userInput.matches("Show the average salary for the department\\s(.+)");

    private DepartmentRepository departmentRepository;

    public AverageSalaryForTheDepartmentCommand(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String executeCommand(String commandContent) throws CommandExecutionException {
        final String departmentName = extractCommandParameter(commandContent);
        Optional<Department> department = departmentRepository.findByName(departmentName);
        if (department.isEmpty()) {
            throw new CommandExecutionException(String.format("Department %s not found", departmentName));
        }

        BigDecimal averageSalaryForDepartment = departmentRepository.getAverageSalaryForDepartment(departmentName);

        return commandInformation.getOutputTemplate()
                .formatted(departmentName, averageSalaryForDepartment.setScale(2, RoundingMode.HALF_UP).toString());
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
