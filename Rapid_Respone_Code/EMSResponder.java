public class EMSResponder extends Responder implements ResponseTime{
    private String certificationLevel;
    private String currentIncidentLocation;
    private int distance;

    //No-arg Constructor
    public EMSResponder(){
        super();
        this.certificationLevel = "";
        this.currentIncidentLocation = "";
        this.distance = 0;
    }

    public EMSResponder(String EMSID, String responderType, String location, boolean availabilityStatus, String certificationLevel, int distance) {
            super(EMSID, "EMS Responder", location, availabilityStatus);
            this.certificationLevel = certificationLevel;
            this.distance = distance;
            this.currentIncidentLocation = null;    //Initially not responding to anything
        }

    //Getter/Setter
    public String getCertificationLevel() {
        return this.certificationLevel;
    }
    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }
    public String getCurrentIncidentLocation() {
        return this.currentIncidentLocation;
    }

    //Respond to call
    @Override
    public void respondToCall(String callerLocation) {
        this.currentIncidentLocation = callerLocation;
        System.out.println("EMS responding to call at: " + currentIncidentLocation);
        setAvailabilityStatus(false);   //Sets availability to false
    }

    public void respondToMedicalIncident(Incident incident) {
        respondToCall(incident.getLocation());
    }

    @Override
    public double calculateResponseTime() {
        return distance; // distance already in minutes
    }

    @Override
    public String getResponseTime() {
        return "EMS Responder will arrive in " + this.calculateResponseTime() + " minutes.";
    }

    @Override
    public String toString(){
        return "EMS Responder{" +
                "ID=" + getResponderId() +
                ", rank='" + certificationLevel + '\'' +
                ", base location='" + getLocation() + '\'' +
                ", current incident location='" + currentIncidentLocation + '\'' +
                ", available=" + this.getAvailabilityStatus() +
                '}';
    }
}
