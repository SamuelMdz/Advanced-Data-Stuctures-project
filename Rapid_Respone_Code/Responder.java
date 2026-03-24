public abstract class Responder {
    private String responderId;
    private String responderType;
    private String location;
    private boolean availabilityStatus;

    public Responder() {
        this.responderId = "";
        this.responderType = "";
        this.location = "";
        this.availabilityStatus = true;
    }

    public Responder(String responderId, String responderType, String location, boolean availabilityStatus) {
        this.responderId = responderId;
        this.responderType = responderType;
        this.location = location;
        this.availabilityStatus = availabilityStatus;
    }

    public String getResponderId() {
        return responderId;
    }

    public String getResponderType() {
        return responderType;
    }

    public String getLocation() {
        return location;
    }

    public boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public abstract void respondToCall(String callerLocation);

    public abstract double calculateResponseTime();

    public abstract String getResponseTime();
}