public abstract class Responder implements Identifiable, ResponseTime{
    private final String responderId;
    private final String responderType;
    private final String location;
    private boolean availabilityStatus;

    //Constructor
    public Responder(String responderId, String responderType, String location, boolean availabilityStatus) {
        this.responderId = responderId;
        this.responderType = responderType;
        this.location = location;
        this.availabilityStatus = availabilityStatus;
    }

    //Getter for responderId
    public String getResponderId() {
        return responderId;
    }

    //Getter for responderType
    public String getResponderType() {
        return responderType;
    }

    //Getter for responder location
    public String getLocation() {
        return location;
    }

    //Getter for availability status
    public boolean isAvailable() {
        return availabilityStatus;
    }

    //Setter for availability status
    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    //Abstract methods for implementation in child classes
    public abstract void respondToCall(String callerLocation);

    public abstract double calculateResponseTime();

    @Override
    public abstract String getResponseTime();

    //Override getId() method in interface Identifiable
    @Override
    public String getId(){
        return responderId;
    }

    //toString() method for output readability
    @Override
    public String toString() {
        return "Responder ID: " + responderId +
                "; Type: " + responderType +
                "; Location: " + location +
                "; Available: " + availabilityStatus;
    }
}