public class PoliceResponder extends Responder implements ResponseTime {
    private String rank;
    public PoliceResponder(String rank) {
        super();
        this.rank = rank;
    }
    public void respondToCrime(Incident incident) {
        System.out.println("PoliceResponder responding to crime incident at: " + incident.getLocation());
    }
    @Override
    public double calculateResponseTime(Incident incident) {return incident.getSeverityLevel() * 1.2;}

    public String getRank() {return rank;}

    public void setRank(String rank) {this.rank = rank;}

    @Override
    public String toString() {return "PoliceResponder{" +
                "rank='" + rank + '\'' + '}';}
}