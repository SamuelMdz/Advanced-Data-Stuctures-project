import java.util.Date;
public class ArrivalRecord implements Comparable<ArrivalRecord> {
    private Responder responder;
    private Incident incident;
    private Date arrivalTime;

    public ArrivalRecord(Responder responder, Incident incident, Date arrivalTime) {
        this.responder = responder;
        this.incident = incident;
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
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
                ", incident=" + incident +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}