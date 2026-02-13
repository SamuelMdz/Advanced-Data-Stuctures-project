public abstract class Responder {
    private final String responderId;
    private final String name;
    private final String location;
    private final String availabilityStatus;

    public Responder(String responderId, String name, String location, String availabilityStatus) {
        this.responderId = responderId;
        this.name = name;
        this.location = location;
        this.availabilityStatus = availabilityStatus;
    }

    public String getResponderId() {
        return responderId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }
}