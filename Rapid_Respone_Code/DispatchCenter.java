import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DispatchCenter {

    private List<Responder> responders;
    private Queue<Incident> incidentQueue;

    public DispatchCenter() {
        this.responders = new ArrayList<>();
        this.incidentQueue = new LinkedList<>();
    }

    // Add a responder to the system
    public void addResponder(Responder responder) {
        responders.add(responder);
    }

    // Add an incident to the system
    public void addIncident(Incident incident) {
        incidentQueue.offer(incident);  //Use offer instead of "add" for queue implementation
    }

    //Returns and removes next incident to show that it was resolved
    public Incident processNextIncident(){
        return incidentQueue.poll();
    }

    //See next incident in queue using peek method for linkedList
    public Incident peekNextIncident(){
        return incidentQueue.peek();
    }

    //Getter for incidentQueue
    public Queue<Incident> getIncidentQueue(){
        return incidentQueue;
    }

    // Assign available responders for a given incident
    public ArrayList<Responder> assignResponders(Incident incident) {
        ArrayList<Responder> assigned = new ArrayList<>();
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
    public ArrayList<ArrivalRecord> getArrivalOrder(Incident incident) {
        ArrayList<ArrivalRecord> arrivalOrder = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        // Assign responders for this incident
        ArrayList<Responder> assigned = assignResponders(incident);

        for (Responder r : assigned) {
            // Calculate arrival time based on responder's distance
            double minutes = ((ResponseTime) r).calculateResponseTime();
            LocalDateTime arrivalTime = now.plusMinutes((long) minutes);

            // Create record
            arrivalOrder.add(new ArrivalRecord(r, incident, arrivalTime));
        }

        return arrivalOrder;
    }
}