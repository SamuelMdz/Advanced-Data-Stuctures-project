import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArrivalRecord implements Comparable<ArrivalRecord> {
    private Responder responder;
    private Incident incident;
    private LocalDateTime arrivalTime;

    //No-arg Constructor
    public ArrivalRecord(){
        this.responder = null;
        this.incident = null;
        this.arrivalTime = LocalDateTime.now();
    }

    //Constructor
    public ArrivalRecord(Responder responder, Incident incident, LocalDateTime arrivalTime) {
        this.responder = responder;
        this.incident = incident;
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");   //Formats date and time to user-friendly formate for readability
    String formattedTimeReported() {
        return arrivalTime.format(formatter);
    }

    public Responder getResponder() {
        return responder;
    }

    public Incident getIncident() {
        return incident;
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
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}