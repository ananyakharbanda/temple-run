package core;

public class Temple {
    private int id;
    private String name;
    private double lat;
    private double lon;

    public Temple(int id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public double getLat() { 
        return lat; 
    }
    public double getLon() { 
        return lon; 
    }
}
