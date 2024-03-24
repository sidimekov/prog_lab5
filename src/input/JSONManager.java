package input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import commandManagers.RouteManager;
import entity.Route;
import util.IdManager;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class JSONManager {
    public static Route readElement(String path) {
        Gson gson = new Gson();
        try {
            Route element = gson.fromJson(new InputStreamReader(new FileInputStream(path)), Route.class);
            JsonObject jsonObject = gson.fromJson(new InputStreamReader(new FileInputStream(path)), JsonObject.class);
            JsonElement id = jsonObject.get("id");
            if (id == null) {
                element.setId(IdManager.getId());
            }
            return element;
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось считать из файла json");
        }
        return null;
    }

    public static void writeElement(String path, Route element) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(element);
        InputManager.write(path, json);
    }

    public static void writeCollection(String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PriorityQueue<Route> collection = RouteManager.getInstance().getCollection();
        String json = gson.toJson(collection);
        InputManager.write(path, json);
    }

    public static PriorityQueue<Route> readCollection(String path) throws RuntimeException{
        Gson gson = new Gson();
        Route[] arrayCollection;
        try {
            arrayCollection = gson.fromJson(new InputStreamReader(new FileInputStream(path)), Route[].class);
            return RouteManager.convertFrom(arrayCollection);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден");
        }
    }
}
