import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArrivalRecord implements Comparable<ArrivalRecord> {
    private Responder responder;
    private Incident incident;
    private LocalDateTime arrivalTime;

    public ArrivalRecord(Responder responder, Incident incident, LocalDateTime arrivalTime) {
        this.responder = responder;
        this.incident = incident;
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");   //Formats date and time to user-friendly formate for readability
    public String formattedTimeReported() {
        return arrivalTime.format(formatter);
    }

    public Responder getResponder() {
        return responder;
    }

    public Incident getIncident() {
        return incident;
    }

    //Helper method in case of alternative manual search implementation
    public String getIncidentId(){
        return incident.getId();
    }

    @Override
    public int compareTo(ArrivalRecord other) {
        return this.arrivalTime.compareTo(other.arrivalTime);
    }

    @Override
    public String toString() {
        return "ArrivalRecord{" +
                "responder=" + responder +
                ", incident=" + incident.getId() +
                ", arrivalTime=" + formattedTimeReported() +
                '}';
    }
}