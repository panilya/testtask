package com.panilya.botscrewtesttask.service.commands;

import com.panilya.botscrewtesttask.database.Degree;
import com.panilya.botscrewtesttask.dto.DepartmentDegreeCountDto;
import com.panilya.botscrewtesttask.repository.DepartmentRepository;
import com.panilya.botscrewtesttask.service.ChatCommand;
import com.panilya.botscrewtesttask.service.CommandInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowStatisticsCommand implements ChatCommand {

    private final CommandInformation commandInformation = CommandInformation.SHOW_STATISTICS_COMMAND;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public ShowStatisticsCommand(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String executeCommand(String commandContent) {
        final String departmentName = extractCommandParameter(commandContent);
        List<DepartmentDegreeCountDto> departmentStatistics = departmentRepository.getDepartmentStatistics(departmentName);
        return commandInformation.getOutputTemplate().formatted(
                departmentStatistics.stream().filter(stat -> stat.getDegree().equals(Degree.ASSISTANT)).map(DepartmentDegreeCountDto::getCount).findFirst().orElse(0L),
                departmentStatistics.stream().filter(stat -> stat.getDegree().equals(Degree.ASSOCIATE_PROFESSOR)).map(DepartmentDegreeCountDto::getCount).findFirst().orElse(0L),
                departmentStatistics.stream().filter(stat -> stat.getDegree().equals(Degree.PROFESSOR)).map(DepartmentDegreeCountDto::getCount).findFirst().orElse(0L));
    }

    @Override
    public CommandInformation getCommandInformation() {
        return commandInformation;
    }

    /*
        5 is the number of characters in word 'show' + 1 whitespace = 5 characters
        11 is the number of characters in word 'statistics' + 1 whitespace
    */
    public String extractCommandParameter(String commandContent) {
        return commandContent.substring(5, commandContent.length() - 11);
    }

}
