import java.util.Comparator;

public class IncidentTypeComparator implements Comparator<Incident> {
    @Override
    public int compare(Incident a1, Incident a2) {
        return a1.getIncidentType().compareTo(a2.getIncidentType());
    }
}
