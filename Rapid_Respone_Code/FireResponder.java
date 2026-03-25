public class FireResponder extends Responder {

    private String currentIncidentLocation; //Tracks where responder is headed
    private String specialization;
    private int distance;   //Some arbitrary distance value to be defined later, not sure how to implement yet

    // Constructor
    public FireResponder(String responderID, String responderType, String location, boolean availabilityStatus, String specialization, int distance) {
        super(responderID, "Fire Responder", location, availabilityStatus);
        this.specialization = specialization;
        this.distance = distance;
        this.currentIncidentLocation = null;    //Initially not responding to anything
    }

    //Getter and Setter for Specialization
    public String getSpecialization() {return specialization;}
    public void setSpecialization(String specialization) {this.specialization = specialization;}

    //Getter for currentIncidentLocation
    public String getCurrentIncidentLocation() {return currentIncidentLocation;}

    //Helper function for respondToCall() function
    public void respondToFire(Incident incident) {
        respondToCall(incident.getLocation());
    }

    @Override
    public void respondToCall(String callerLocation) {
        this.currentIncidentLocation = callerLocation; // track where heading
        System.out.println("Fire Responder responding to call incident at: " + callerLocation);
        setAvailabilityStatus(false);
    }

    @Override
    public double calculateResponseTime() {
        return distance; // distance already in minutes
    }

    @Override
    public String getResponseTime() {   //Returns response time
        return "Fire Responder will arrive in " + this.calculateResponseTime() + " minutes.";
    }

    @Override
    public String toString(){
        return "Fire Responder{" +
                "ID:" + getResponderId() +
                ", Specialization:'" + specialization + '\'' +
                ", Base Location:'" + getLocation() + '\'' +
                ", Current Incident Location:'" + currentIncidentLocation + '\'' +
                ", Availability:" + isAvailable() +
                '}';
    }
}