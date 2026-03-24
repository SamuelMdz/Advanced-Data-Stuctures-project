public class PoliceResponder extends Responder implements ResponseTime {
    private String rank;
    private String currentIncidentLocation; //Tracks where responder is heading
    private int distance;  //Some arbitrary distance value to be defined later, not sure how to implement yet

    //No-arg Constructor
    public PoliceResponder(){
        super();
        this.rank = "";
        this.currentIncidentLocation = "";
        this.distance = 0;
    }

    public PoliceResponder(String policeID, String responderType, String location, boolean availabilityStatus, String rank, int distance) {
        super(policeID, "Police Responder", location, availabilityStatus);
        this.rank = rank;
        this.distance = distance;
        this.currentIncidentLocation = null;    //Initially not responding to anything
    }

    public String getRank() {
        return rank;}

    public void setRank(String rank) {
        this.rank = rank;}

    public String getCurrentIncidentLocation() {
        return currentIncidentLocation;}

    @Override
    public void respondToCall(String callerLocation) {
        this.currentIncidentLocation = callerLocation;
        System.out.println("Police Responder responding to call at: " + currentIncidentLocation);
        setAvailabilityStatus(false);   //Sets availability to false
    }

    public void respondToCrime(Incident incident) {
        respondToCall(incident.getLocation());
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
                ", rank='" + rank + '\'' +
                ", base location='" + getLocation() + '\'' +
                ", current incident location='" + currentIncidentLocation + '\'' +
                ", available=" + getAvailabilityStatus() +
                '}';
    }
}