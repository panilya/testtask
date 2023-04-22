package com.panilya.botscrewtesttask;

import com.panilya.botscrewtesttask.service.CommandHandler;
import com.panilya.botscrewtesttask.exception.CommandNotFoundException;
import com.panilya.botscrewtesttask.fakedata.DataPreInitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Profile({"prod", "dev"})
@Service
public class ChatBotRunner implements CommandLineRunner {

    private final CommandHandler commandHandler;

    private final DataPreInitializationService dataPreInitializationService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    public ChatBotRunner(CommandHandler commandHandler, DataPreInitializationService dataPreInitializationService) {
        this.commandHandler = commandHandler;
        this.dataPreInitializationService = dataPreInitializationService;
    }

    @Override
    public void run(String... args) {
        if (activeProfile.equals("dev")) {
            dataPreInitializationService.initializeData();
        }

        System.out.println("\nHello! Welcome to the university chat bot!");
        System.out.println("\nEnter one of the following commands:");
        System.out.println("[1] - Who is head of department {department_name}");
        System.out.println("[2] - Show {department_name} statistics");
        System.out.println("[3] - Show the average salary for the department {department_name}");
        System.out.println("[4] - Show count of employee for {department_name}");
        System.out.println("[5] - Global search by {template}");
        System.out.println("\n" + "You can also enter number of command to execute it and the parameter" + " (For example: 1 Mathematics)");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();
                if (command.equals("stop")) {
                    break;
                }
                try {
                    System.out.println(commandHandler.handleCommand(command));
                } catch (CommandNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Something went wrong. Check again your message!");
                }
            }
        }

    }
}
