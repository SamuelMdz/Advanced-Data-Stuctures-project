import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DispatchCenter {
    private List<Responder> responders;
    private List<Incident> activeIncidents;

    //Constructor
    public DispatchCenter() {
        this.responders = new ArrayList<>();
        this.activeIncidents = new ArrayList<>();
    }

    // Add a responder to the system
    public void addResponder(Responder responder) {
        responders.add(responder);
    }

    // Add an incident to the system
    public void addIncident(Incident incident) {
        activeIncidents.add(incident);
    }

    // Assign available responders for a given incident
    public List<Responder> assignResponders(Incident incident) {
        List<Responder> assigned = new ArrayList<>();
        for (Responder r : responders) {
            if (r.getAvailabilityStatus()) {
                // Update responder to be en route
                if (r instanceof FireResponder) ((FireResponder) r).respondToCall(incident.getLocation());
                if (r instanceof PoliceResponder) ((PoliceResponder) r).respondToCall(incident.getLocation());
                if (r instanceof EMSResponder) ((EMSResponder) r).respondToCall(incident.getLocation());

                assigned.add(r);
            }
        }
        return assigned;
    }

    // Get responders ordered by arrival time for a given incident
    public List<ArrivalRecord> getArrivalOrder(Incident incident) {
        List<ArrivalRecord> arrivalOrder = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        // Assign responders for this incident
        List<Responder> assigned = assignResponders(incident);

        for (Responder r : assigned) {
            // Calculate arrival time based on responder's distance
            double minutes = ((ResponseTime) r).calculateResponseTime();
            LocalDateTime arrivalTime = now.plusMinutes((long) minutes);

            // Create record
            arrivalOrder.add(new ArrivalRecord(r, incident, arrivalTime));
        }

        // Sort by arrival time (ArrivalRecord implements Comparable)
        arrivalOrder.sort(null);

        return arrivalOrder;
    }
}