package commandManagers;

import commandManagers.commands.*;
import enums.ReadModes;
import input.InputManager;
import interfaces.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private Map<String, Command> commands;
    private static CommandInvoker instance;

    private CommandInvoker() {
        RouteManager.getInstance();

        commands = new HashMap<String, Command>();

        commands.put("help", new HelpCommand());
        commands.put("add", new AddCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("update", new UpdateIdCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("save", new SaveCommand());
    }

    public static CommandInvoker getInstance() {
        if (instance == null) {
            instance = new CommandInvoker();
        }
        return instance;
    }

    public void listenCommands() {
        try (BufferedReader reader = InputManager.getConsoleReader()) {
            while (true) {
                String line = reader.readLine();
                runCommand(line, ReadModes.CONSOLE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runCommand(String line, ReadModes readMode) {
        String[] tokens = line.split(" ");
        Command command = commands.get(tokens[0]);
        if (command != null) {
            if (tokens.length > 1) {
                command.execute(readMode, Arrays.copyOfRange(tokens, 1, tokens.length));
            } else {
                command.execute(readMode, new String[0]);
            }
        } else {
            System.out.println("Такой команды не существует!");
        }
    }
    public Map<String, Command> getCommands() {
        return commands;
    }
}

