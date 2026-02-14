import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Incident implements Comparable<Incident>, Cloneable {

    public enum SeverityLevel {CRITICAL, HIGH, MEDIUM, LOW};
    private final String incidentId;
    private String incidentType;
    private SeverityLevel severityLevel;
    private String location;
    private final LocalDateTime timeReported;
    private Caller caller;
    private static int incidentCounter = 0;

    public Incident(String incidentType, SeverityLevel severityLevel,
                    String location, Caller caller) {
        this.incidentId = String.format("INC%04d", ++incidentCounter);    //Returns an auto-generated ID of type String using the incremented static incidentCounter variable
        this.incidentType = incidentType;
        this.severityLevel = severityLevel;
        this.location = location;
        this.caller = caller;
        this.timeReported = LocalDateTime.now();
    }

    public String getId() {
        return incidentId;
    }

    public SeverityLevel getSeverityLevel() {
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

    public void setSeverityLevel(SeverityLevel severity) {
        this.severityLevel = severity;
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");   //Formats date and time to user-friendly formate for readability
    String formattedTimeReported() {
        return timeReported.format(formatter);
    }

    public LocalDateTime getTimeReported() {
        return timeReported;
    }

    public static int getIncidentCounter() {
        return incidentCounter;
    }

    @Override
    public int compareTo(Incident other) {  //Makes sort use the severity level of each incident to determine their priority
        int s = Integer.compare(this.severityLevel.ordinal(), other.severityLevel.ordinal());
        if (s != 0) return s;   //If the severity levels are not even, then return the integer to Sort method
        int result = this.timeReported.compareTo(other.timeReported);   //If the everity levels are the same, return whichever Incident was made first
        return result;  //Return result of timeComparison result
    }

    @Override
    public Incident clone() throws CloneNotSupportedException{  //Clones the incident in case modifications need to be made, but keeps original timeReported
        Incident copy = (Incident) super.clone();
        return copy;
    }

    @Override
    public String toString() {
        return "Incident Id: '" + incidentId +
                "; Type of Incident: '" + incidentType + '\'' +
                "; Severity of Incident: " + severityLevel +
                "; Location: '" + location + '\'' +
                "; Time Reported: " + formattedTimeReported() +
                "; Caller: " + caller;
    }
}