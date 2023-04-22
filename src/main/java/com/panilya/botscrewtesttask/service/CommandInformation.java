package com.panilya.botscrewtesttask.service;

import java.util.function.Predicate;

public enum CommandInformation {
    HEAD_OF_DEPARTMENT_COMMAND("Who is head of department %s", "Head of %s department is %s"),
    SHOW_STATISTICS_COMMAND("Show %s statistics", """
            assistants - %d
            associate professors - %d
            professors - %d"""),
    SHOW_AVERAGE_SALARY_FOR_THE_DEPARTMENT_COMMAND("Show the average salary for the department %s",
            "The average salary of %s department is %s"),
    COUNT_OF_EMPLOYEE_FOR_THE_DEPARTMENT_COMMAND("Show count of employee for %s",
            "Answer: %d"),
    GLOBAL_SEARCH_COMMAND("Global search by %s", "Answer: %s");

    private final String inputTemplate;

    private final String outputTemplate;

    CommandInformation(String inputTemplate, String outputTemplate) {
        this.inputTemplate = inputTemplate;
        this.outputTemplate = outputTemplate;
    }

    public String getInputTemplate() {
        return inputTemplate;
    }

    public String getOutputTemplate() {
        return outputTemplate;
    }

}