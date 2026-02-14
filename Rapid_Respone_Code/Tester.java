import java.time.format.DateTimeFormatter;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        // Formatter for printing arrival times nicely
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // --- Create responders ---
        FireResponder f1 = new FireResponder("F001", "Fire Responder", "Fire Station A", true, "Hose Specialist", 3);
        FireResponder f2 = new FireResponder("F002", "Fire Responder", "Fire Station B", true, "Ladder Specialist", 7);
        PoliceResponder p1 = new PoliceResponder("P001", "Police Responder", "Police HQ", true, "Sergeant", 5);
        PoliceResponder p2 = new PoliceResponder("P002", "Police Responder", "Police HQ", true, "Lieutenant", 10);
        EMSResponder e1 = new EMSResponder("E001", "EMS Responder", "EMS Station", true, "Paramedic", 4);
        EMSResponder e2 = new EMSResponder("E002", "EMS Responder", "EMS Station", true, "EMT", 6);

        // --- Create incidents ---
        Incident fireIncident = new Incident("Fire", Incident.SeverityLevel.CRITICAL, "5th Street", null);
        Incident crimeIncident = new Incident("Crime", Incident.SeverityLevel.HIGH, "7th Avenue", null);
        Incident medicalIncident = new Incident("Medical", Incident.SeverityLevel.MEDIUM, "10th Street", null);

        // --- Setup dispatch center ---
        DispatchCenter dispatch = new DispatchCenter();
        dispatch.addResponder(f1);
        dispatch.addResponder(f2);
        dispatch.addResponder(p1);
        dispatch.addResponder(p2);
        dispatch.addResponder(e1);
        dispatch.addResponder(e2);

        // --- Add incidents ---
        dispatch.addIncident(fireIncident);
        dispatch.addIncident(crimeIncident);
        dispatch.addIncident(medicalIncident);

        // --- Simulate each incident ---
        System.out.println("=== Fire Incident Arrival Order ===");
        List<ArrivalRecord> fireArrival = dispatch.getArrivalOrder(fireIncident);
        fireArrival.forEach(a -> System.out.println(
                a.getResponder().getResponderId() + " (" + a.getResponder().getClass().getSimpleName() + ") -> " +
                        a.getArrivalTime().format(formatter)
        ));

        // Reset availability for next incident
        f1.setAvailabilityStatus(true);
        f2.setAvailabilityStatus(true);
        p1.setAvailabilityStatus(true);
        p2.setAvailabilityStatus(true);
        e1.setAvailabilityStatus(true);
        e2.setAvailabilityStatus(true);

        System.out.println("\n=== Crime Incident Arrival Order ===");
        List<ArrivalRecord> crimeArrival = dispatch.getArrivalOrder(crimeIncident);
        crimeArrival.forEach(a -> System.out.println(
                a.getResponder().getResponderId() + " (" + a.getResponder().getClass().getSimpleName() + ") -> " +
                        a.getArrivalTime().format(formatter)
        ));

        // Reset availability for next incident
        f1.setAvailabilityStatus(true);
        f2.setAvailabilityStatus(true);
        p1.setAvailabilityStatus(true);
        p2.setAvailabilityStatus(true);
        e1.setAvailabilityStatus(true);
        e2.setAvailabilityStatus(true);

        System.out.println("\n=== Medical Incident Arrival Order ===");
        List<ArrivalRecord> medicalArrival = dispatch.getArrivalOrder(medicalIncident);
        medicalArrival.forEach(a -> System.out.println(
                a.getResponder().getResponderId() + " (" + a.getResponder().getClass().getSimpleName() + ") -> " +
                        a.getArrivalTime().format(formatter)
        ));
    }
}