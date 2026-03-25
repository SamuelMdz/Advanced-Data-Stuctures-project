public class PoliceResponder extends Responder {
    private String rank;
    private String currentIncidentLocation; //Tracks where responder is heading
    private int distance;  //Some arbitrary distance value to be defined later, not sure how to implement yet

    public PoliceResponder(String policeID, String responderType, String location, boolean availabilityStatus, String rank, int distance) {
        super(policeID, "Police Responder", location, availabilityStatus);
        this.rank = rank;
        this.distance = distance;
        this.currentIncidentLocation = null;    //Initially not responding to anything
    }

    //Getters
    public String getRank() {return rank;}
    public void setRank(String rank) {this.rank = rank;}
    public String getCurrentIncidentLocation() {return currentIncidentLocation;}

    //Helper function for respondToCall() function
    public void respondToCrime(Incident incident) {
        respondToCall(incident.getLocation());
    }

    @Override
    public void respondToCall(String callerLocation) {
        this.currentIncidentLocation = callerLocation;
        System.out.println("Police Responder responding to call at: " + currentIncidentLocation);
        setAvailabilityStatus(false);   //Sets availability to false
    }

    @Override
    public double calculateResponseTime() {
        return distance; // distance already in minutes
    }

    @Override
    public String getResponseTime() {
        return "Police Responder will arrive in " + this.calculateResponseTime() + " minutes.";
    }

    @Override
    public String toString(){
        return "Police Responder{" +
                "ID=" + getResponderId() +
                ", Rank:'" + rank + '\'' +
                ", Base Location:'" + getLocation() + '\'' +
                ", Current Incident Location:'" + currentIncidentLocation + '\'' +
                ", Availability:" + isAvailable() +
                '}';
    }
}