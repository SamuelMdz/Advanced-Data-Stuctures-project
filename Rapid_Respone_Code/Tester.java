import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Tester {
    public static void main(String[] args) {

        DispatchCenter dispatch = new DispatchCenter();

        System.out.println("=========================================");
        System.out.println("RAPID RESPONSE PROJECT TESTER");
        System.out.println("=========================================");

        // =========================================
        // RESPONDER / POLYMORPHISM DEMO
        // =========================================
        System.out.println("\n=========================================");
        System.out.println("RESPONDER / POLYMORPHISM DEMO");
        System.out.println("=========================================");

        Responder fire1 = new FireResponder("F001", "Fire Responder", "Central Fire Station", true, "Engine Specialist", 3);
        Responder fire2 = new FireResponder("F002", "Fire Responder", "West Fire Station", true, "Ladder Specialist", 7);

        Responder police1 = new PoliceResponder("P001", "Police Responder", "North Police Station", true, "Sergeant", 5);
        Responder police2 = new PoliceResponder("P002", "Police Responder", "South Police Station", true, "Lieutenant", 10);

        Responder ems1 = new EMSResponder("E001", "EMS Responder", "East EMS Station", true, "Paramedic", 4);
        Responder ems2 = new EMSResponder("E002", "EMS Responder", "West EMS Station", true, "EMT", 6);

        dispatch.addResponder(fire1);
        dispatch.addResponder(fire2);
        dispatch.addResponder(police1);
        dispatch.addResponder(police2);
        dispatch.addResponder(ems1);
        dispatch.addResponder(ems2);

        System.out.println(fire1);
        System.out.println(police1);
        System.out.println(ems1);

        if (fire1 instanceof FireResponder) {
            FireResponder fireCast = (FireResponder) fire1;
            System.out.println("\nSafe casting with instanceof:");
            System.out.println("Fire specialization: " + fireCast.getSpecialization());
        }

        // =========================================
        // CALLER / HASHMAP DEMO THROUGH DISPATCHCENTER
        // =========================================
        System.out.println("\n=========================================");
        System.out.println("CALLER REGISTRY / HASHMAP DEMO");
        System.out.println("=========================================");

        Caller caller1 = new Caller("Alice Johnson", "713-111-1111", "Downtown District");
        Caller caller2 = new Caller("Brian Smith", "713-222-2222", "River Market");
        Caller caller3 = new Caller("Carla Davis", "713-333-3333", "Westside Hospital");
        Caller caller4 = new Caller("Derek Lee", "713-444-4444", "South Residential Area");
        Caller caller5 = new Caller("Elena Cruz", "713-555-5555", "Main Street");

        dispatch.addCaller(caller1);
        dispatch.addCaller(caller2);
        dispatch.addCaller(caller3);
        dispatch.addCaller(caller4);
        dispatch.addCaller(caller5);

        System.out.println("Found caller:");
        System.out.println(dispatch.getCallerById(caller1.getId()));

        Caller missingCaller = dispatch.getCallerById("C9999");
        if (missingCaller == null) {
            System.out.println("\nMissing caller handled correctly: C9999 not found.");
        }

        dispatch.updateCallerPhoneNumber(caller1.getId(), "713-999-9999");
        System.out.println("\nUpdated caller phone number:");
        System.out.println(dispatch.getCallerById(caller1.getId()));

        dispatch.updateCallerLocation(caller3.getId(), "Downtown Houston");
        System.out.println("\nUpdated caller location:");
        System.out.println(dispatch.getCallerById(caller3.getId()));

        boolean removedCaller = dispatch.removeCaller(caller2.getId());
        if (removedCaller) {
            System.out.println("\nRemoved caller from registry: " + caller2.getId());
        }

        // =========================================
        // INCIDENT / COLLECTIONS / AVL DEMO
        // =========================================
        System.out.println("\n=========================================");
        System.out.println("INCIDENT COLLECTIONS AND AVL DEMO");
        System.out.println("=========================================");

        Incident incident1 = new Incident("Fire", Incident.SeverityLevel.CRITICAL, "Downtown District", caller1);
        Incident incident2 = new Incident("Crime", Incident.SeverityLevel.HIGH, "River Market", caller2);
        Incident incident3 = new Incident("Medical", Incident.SeverityLevel.MEDIUM, "Westside Hospital", caller3);
        Incident incident4 = new Incident("Traffic", Incident.SeverityLevel.LOW, "South Residential Area", caller4);
        Incident incident5 = new Incident("Crime", Incident.SeverityLevel.CRITICAL, "Main Street", caller5);

        dispatch.addIncident(incident1);
        dispatch.addIncident(incident2);
        dispatch.addIncident(incident3);
        dispatch.addIncident(incident4);
        dispatch.addIncident(incident5);

        System.out.println("Total incidents in ArrayList: " + dispatch.getIncidents().size());
        System.out.println("Total incidents in Queue: " + dispatch.getIncidentQueue().size());

        System.out.println("\nNext incident in queue:");
        System.out.println(dispatch.peekNextIncident());

        System.out.println("\nProcessing next incident:");
        System.out.println(dispatch.processNextIncident());

        System.out.println("\nNew front of queue:");
        System.out.println(dispatch.peekNextIncident());

        System.out.println("\nCritical incidents found with Iterator:");
        for (Incident incident : dispatch.getCriticalIncidents()) {
            System.out.println(incident);
        }

        System.out.println("\nAVL search for existing incident:");
        System.out.println(dispatch.searchIncidentAVL(incident3.getId()));

        Incident missingAVL = dispatch.searchIncidentAVL("INC9999");
        if (missingAVL == null) {
            System.out.println("\nMissing AVL incident handled correctly: INC9999 not found.");
        }

        boolean deletedAVL = dispatch.deleteIncidentAVL(incident2.getId());
        if (deletedAVL) {
            System.out.println("\nDeleted incident from AVL tree: " + incident2.getId());
        }

        System.out.println("\nAVL inorder traversal:");
        for (Incident incident : dispatch.getIncidentsInAVLOrder()) {
            System.out.println(incident);
        }

        // =========================================
        // RESPONSE NETWORK GRAPH DEMO THROUGH DISPATCHCENTER
        // =========================================
        System.out.println("\n=========================================");
        System.out.println("WEIGHTED GRAPH / DIJKSTRA / PRIM DEMO");
        System.out.println("=========================================");

        ResponseNetworkGraph graph = dispatch.getResponseGraph();

        graph.addLocation("Central Fire Station");
        graph.addLocation("North Police Station");
        graph.addLocation("East EMS Station");
        graph.addLocation("Downtown District");
        graph.addLocation("River Market");
        graph.addLocation("Westside Hospital");
        graph.addLocation("South Residential Area");

        graph.addRoute(0, 3, 4);
        graph.addRoute(0, 4, 6);
        graph.addRoute(1, 3, 5);
        graph.addRoute(1, 6, 9);
        graph.addRoute(2, 4, 3);
        graph.addRoute(2, 5, 8);
        graph.addRoute(3, 4, 2);
        graph.addRoute(3, 5, 7);
        graph.addRoute(4, 6, 6);
        graph.addRoute(5, 6, 4);

        graph.printRoutes();

        int sourceLocation = graph.getLocationIndex("Central Fire Station");
        int destinationLocation = graph.getLocationIndex("Westside Hospital");

        System.out.println("\nDijkstra shortest path:");
        ResponseNetworkGraph.ShortestResponsePath shortestPath =
                graph.getShortestPath(sourceLocation);
        shortestPath.printPathTo(destinationLocation);

        System.out.println("\nPrim minimum response network:");
        ResponseNetworkGraph.MinimumResponseNetwork minimumNetwork =
                graph.getMinimumResponseNetwork(sourceLocation);
        minimumNetwork.printMinimumResponseNetwork();

        // =========================================
        // ARRIVAL RECORD / SORTING DEMO
        // =========================================
        System.out.println("\n=========================================");
        System.out.println("ARRIVAL RECORD SORTING DEMO");
        System.out.println("=========================================");

        fire1.setAvailabilityStatus(true);
        fire2.setAvailabilityStatus(true);
        police1.setAvailabilityStatus(true);
        police2.setAvailabilityStatus(true);
        ems1.setAvailabilityStatus(true);
        ems2.setAvailabilityStatus(true);

        ArrayList<ArrivalRecord> arrivalRecords = dispatch.getArrivalOrder(incident3);

        System.out.println("Before sorting:");
        for (ArrivalRecord record : arrivalRecords) {
            System.out.println(record);
        }

        ArrayList<ArrivalRecord> mergeSortedArrivals = new ArrayList<>(arrivalRecords);
        DispatchCenter.mergeSortArrival(mergeSortedArrivals);

        System.out.println("\nAfter merge sort:");
        for (ArrivalRecord record : mergeSortedArrivals) {
            System.out.println(record);
        }

        ArrayList<ArrivalRecord> quickSortedArrivals = new ArrayList<>(arrivalRecords);
        DispatchCenter.quickSort(quickSortedArrivals);

        System.out.println("\nAfter quick sort:");
        for (ArrivalRecord record : quickSortedArrivals) {
            System.out.println(record);
        }

        // =========================================
        // INCIDENT SORTING / SEARCHING DEMO
        // =========================================
        System.out.println("\n=========================================");
        System.out.println("INCIDENT SORTING AND SEARCHING DEMO");
        System.out.println("=========================================");

        ArrayList<Incident> incidentsByType = new ArrayList<>(dispatch.getIncidents());
        incidentsByType.sort(new IncidentTypeComparator());

        System.out.println("Incidents sorted by type:");
        for (Incident incident : incidentsByType) {
            System.out.println(incident);
        }

        ArrayList<Incident> heapSortedIncidents = new ArrayList<>(dispatch.getIncidents());
        DispatchCenter.heapSortIncidents(heapSortedIncidents);

        System.out.println("\nIncidents after heap sort:");
        for (Incident incident : heapSortedIncidents) {
            System.out.println(incident);
        }

        ArrayList<Incident> incidentSearchList = new ArrayList<>(dispatch.getIncidents());
        incidentSearchList.sort(Comparator.comparing(Incident::getId));

        int foundIncidentIndex = DispatchCenter.binarySearchIncident(
                incidentSearchList,
                incident3.getId(),
                0,
                incidentSearchList.size() - 1
        );

        if (foundIncidentIndex != -1) {
            System.out.println("\nBinary search found incident:");
            System.out.println(incidentSearchList.get(foundIncidentIndex));
        }

        int missingIncidentIndex = DispatchCenter.binarySearchIncident(
                incidentSearchList,
                "INC9999",
                0,
                incidentSearchList.size() - 1
        );

        if (missingIncidentIndex == -1) {
            System.out.println("\nBinary search handled missing incident correctly.");
        }

        // =========================================
        // ARRIVAL RECORD BINARY SEARCH DEMO
        // =========================================
        System.out.println("\n=========================================");
        System.out.println("ARRIVAL RECORD BINARY SEARCH DEMO");
        System.out.println("=========================================");

        ArrayList<ArrivalRecord> arrivalSearchList = new ArrayList<>();

        arrivalSearchList.add(new ArrivalRecord(fire1, incident1, LocalDateTime.now().plusMinutes(3)));
        arrivalSearchList.add(new ArrivalRecord(police1, incident2, LocalDateTime.now().plusMinutes(5)));
        arrivalSearchList.add(new ArrivalRecord(ems1, incident3, LocalDateTime.now().plusMinutes(4)));
        arrivalSearchList.add(new ArrivalRecord(fire2, incident4, LocalDateTime.now().plusMinutes(7)));
        arrivalSearchList.add(new ArrivalRecord(police2, incident5, LocalDateTime.now().plusMinutes(10)));

        arrivalSearchList.sort(Comparator.comparing(ArrivalRecord::getIncidentId));

        int foundArrivalIndex = DispatchCenter.binarySearchArrival(
                arrivalSearchList,
                incident3.getId(),
                0,
                arrivalSearchList.size() - 1
        );

        if (foundArrivalIndex != -1) {
            System.out.println("Binary search found arrival record:");
            System.out.println(arrivalSearchList.get(foundArrivalIndex));
        }

        // =========================================
        // BENCHMARKS
        // =========================================
        System.out.println("\n=========================================");
        System.out.println("BENCHMARK - HEAP SORT VS COLLECTIONS.SORT()");
        System.out.println("=========================================");

        int benchmarkSize = 10000;
        Random random = new Random();
        ArrayList<Incident> benchmarkIncidents = new ArrayList<>();

        for (int i = 0; i < benchmarkSize; i++) {
            Incident.SeverityLevel randomSeverity =
                    Incident.SeverityLevel.values()[random.nextInt(Incident.SeverityLevel.values().length)];

            String incidentType;

            int typeChoice = random.nextInt(4);
            if (typeChoice == 0) {
                incidentType = "Fire";
            } else if (typeChoice == 1) {
                incidentType = "Crime";
            } else if (typeChoice == 2) {
                incidentType = "Medical";
            } else {
                incidentType = "Traffic";
            }

            benchmarkIncidents.add(
                    new Incident(
                            incidentType,
                            randomSeverity,
                            "Benchmark Location " + i,
                            null
                    )
            );
        }

        ArrayList<Incident> manualSortList = new ArrayList<>(benchmarkIncidents);
        ArrayList<Incident> javaSortList = new ArrayList<>(benchmarkIncidents);

        long manualStart = System.currentTimeMillis();
        DispatchCenter.heapSortIncidents(manualSortList);
        long manualEnd = System.currentTimeMillis();

        long javaStart = System.currentTimeMillis();
        Collections.sort(javaSortList);
        long javaEnd = System.currentTimeMillis();

        System.out.println("Dataset size: " + benchmarkSize);
        System.out.println("Manual Heap Sort time: " + (manualEnd - manualStart) + " ms");
        System.out.println("Collections.sort() time: " + (javaEnd - javaStart) + " ms");

        System.out.println("\n=========================================");
        System.out.println("TESTING COMPLETE");
        System.out.println("=========================================");
    }
}