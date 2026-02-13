import java.util.Date;

public class Incident
    implements Comparable<Incident>, Cloneable, Identifiable{
    private String incidentID;
    private String incidentType;
    private int severityLevel;
    private String location;
    private Date timeReported;

    public Incident(String incidentID, String incidentType, int severityLevel, String location){
        this.incidentID = incidentID;
        this.incidentType = incidentType;
        this.severityLevel = severityLevel;
        this.location = location;
        this.timeReported = new Date();
    }

}