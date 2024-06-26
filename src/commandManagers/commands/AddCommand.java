package commandManagers.commands;

import Exceptions.FailedJSONReadException;
import Exceptions.FailedValidationException;
import entity.Route;
import enums.ReadModes;
import input.InputManager;
import input.JSONManager;
import interfaces.Command;
import commandManagers.RouteManager;

import java.io.BufferedReader;
import java.io.IOException;

public class AddCommand implements Command {
    private final String USAGE = "add ИЛИ add <элемент в формате .json>";
    private final String DESC = "добавить новый элемент в коллекцию";

    @Override
    public void execute(ReadModes readMode, String[] args) {
        RouteManager rm = RouteManager.getInstance();
        if (args.length == 0) {
            try {
                BufferedReader reader = InputManager.getConsoleReader();
                Route element = RouteManager.buildNew(reader); // если с консоли
                rm.addElement(element, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // из файла .json
            String path = args[0];
            try {
                Route element = JSONManager.readElement(path);
                RouteManager.getInstance().addElement(element);
            } catch (FailedValidationException | FailedJSONReadException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        if (readMode == ReadModes.CONSOLE) {
            System.out.println("Добавлен элемент в коллекцию");
        }
    }

    public String getDesc() {
        return DESC;
    }

    public String getUsage() {
        return USAGE;
    }
}
