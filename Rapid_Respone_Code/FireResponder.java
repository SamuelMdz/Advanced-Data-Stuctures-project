public class FireResponder extends Responder implements ResponseTime {

    // Attributes
    private String specialization;

    // Constructor
    public FireResponder(String specialization) {
        super();
        this.specialization = specialization;
    }

    // Methods
    public void respondToFire(Incident incident) {
        System.out.println("FireResponder responding to fire incident at: " + incident.getLocation());
    }

    @Override
    public double calculateResponseTime(Incident incident) {return incident.getSeverityLevel() * 1.5;}

    public String getSpecialization() {return specialization;}

    public void setSpecialization(String specialization) {this.specialization = specialization;}

    @Override
    public String toString() {return "FireResponder{" +
                "specialization='" + specialization + '\'' +
                '}';}
}