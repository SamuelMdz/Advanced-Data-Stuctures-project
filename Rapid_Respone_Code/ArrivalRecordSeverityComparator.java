import java.util.Comparator;

public class ArrivalRecordSeverityComparator implements Comparator<ArrivalRecord> {

    //Overriding comparator to make an alternative comparator for a different sorting order based on severity of incident
    @Override
    public int compare(ArrivalRecord a1, ArrivalRecord a2) {
        return Integer.compare(a1.getIncident().getSeverityLevel().ordinal(), a2.getIncident().getSeverityLevel().ordinal());
    }
}
