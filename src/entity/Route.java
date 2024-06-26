package entity;

import commandManagers.RouteManager;
import util.IdManager;

import java.util.Date;

public class Route implements Comparable {
    private long id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private LocationFrom from; //Поле не может быть null
    private LocationTo to; //Поле может быть null
    private double distance; //Значение поля должно быть больше 1

    public Route(String name, Coordinates coordinates, LocationFrom from, LocationTo to, double distance) {
        this.id = IdManager.getId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(long id, String name, Coordinates coordinates, LocationFrom from, LocationTo to, double distance) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public LocationFrom getFrom() {
        return from;
    }

    public void setFrom(LocationFrom from) {
        this.from = from;
    }

    public LocationTo getTo() {
        return to;
    }

    public void setTo(LocationTo to) {
        this.to = to;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    public static boolean checkId(long id) {
        if (RouteManager.isInitialized()) {
            return (id > 0 && !RouteManager.getInstance().getIds().contains(id));
        } else {
            return (id > 0);
        }
    }
    public static boolean checkName(String name) {
        return (name != null && !name.isEmpty());
    }
    public static boolean checkCoordinates(Coordinates coordinates) {
        return (coordinates != null);
    }
    public static boolean checkCreationDate(Date creationDate) {
        return (creationDate != null);
    }
    public static boolean checkFrom(LocationFrom from) {
        return (from != null);
    }
    public static boolean checkTo(LocationTo to) {
        return true;
    }
    public static boolean checkDistance(double distance) {
        return (distance > 1);
    }



    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(Object obj) {
        Route route2 = (Route) obj;
        Route route1 = this;
        double distDiff = route1.getDistance() - route2.getDistance();
        if (distDiff > 0) {
            return 1;
        } else if (distDiff < 0) {
            return -1;
        } else {
            return route1.getName().compareTo(route2.getName());
        }
    }
}
