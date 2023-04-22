package com.panilya.botscrewtesttask.service;

import java.util.function.Predicate;

public enum CommandInformation {
    HEAD_OF_DEPARTMENT_COMMAND("Who is head of department %s", "Head of %s department is %s",
            userInput -> userInput.matches("Who is head of department\\s(.+)")),
    SHOW_STATISTICS_COMMAND("Show %s statistics", """
            assistants - %d
            associate professors - %d
            professors - %d""",
            userInput -> userInput.matches("Show\\s(.+)\\sstatistics")),
    SHOW_AVERAGE_SALARY_FOR_THE_DEPARTMENT_COMMAND("Show the average salary for the department %s",
            "The average salary of %s department is %s",
            userInput -> userInput.matches("Show the average salary for the department\\s(.+)")),
    COUNT_OF_EMPLOYEE_FOR_THE_DEPARTMENT_COMMAND("Show count of employee for %s",
            "Answer: %d",
            userInput -> userInput.matches("Show count of employee for\\s(.+)")),
    GLOBAL_SEARCH_COMMAND("Global search by %s",
            "Answer: %s",
            userInput -> userInput.matches("Global search by\\s(.+)"));

    private final String inputTemplate;

    private final String outputTemplate;

    private final Predicate<String> satisfactionPredicate;

    CommandInformation(String inputTemplate, String outputTemplate, Predicate<String> satisfactionPredicate) {
        this.inputTemplate = inputTemplate;
        this.outputTemplate = outputTemplate;
        this.satisfactionPredicate = satisfactionPredicate;
    }

    public String getInputTemplate() {
        return inputTemplate;
    }

    public String getOutputTemplate() {
        return outputTemplate;
    }

    public Predicate<String> getSatisfactionPredicate() {
        return satisfactionPredicate;
    }
}