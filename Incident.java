import java.util.Date;

public class Incident implements Comparable<Incident>, Cloneable {

    private int incidentId;
    private String incidentType;
    private int severityLevel;
    private String location;
    private Date timeReported;
    private Caller caller;

    public Incident(int incidentId, String incidentType, int severityLevel,
                    String location, Date timeReported, Caller caller) {
        this.incidentId = incidentId;
        this.incidentType = incidentType;
        this.severityLevel = severityLevel;
        this.location = location;
        this.timeReported = timeReported;
        this.caller = caller;
    }

    public int getId() {
        return incidentId;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public String getLocation() {
        return location;
    }

    public Caller getCaller() {
        return caller;
    }

    public void setSeverityLevel(int severity) {
        this.severityLevel = severity;
    }

    @Override
    public int compareTo(Incident other) {
        return Integer.compare(this.severityLevel, other.severityLevel);
    }

    @Override
    public Incident clone() {
        try {
            Incident cloned = (Incident) super.clone();
            // Deep copy mutable fields
            cloned.timeReported = (Date) this.timeReported.clone();
            cloned.caller = this.caller.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    @Override
    public String toString() {
        return "Incident{" +
                "incidentId=" + incidentId +
                ", incidentType='" + incidentType + '\'' +
                ", severityLevel=" + severityLevel +
                ", location='" + location + '\'' +
                ", timeReported=" + timeReported +
                ", caller=" + caller +
                '}';
    }
}