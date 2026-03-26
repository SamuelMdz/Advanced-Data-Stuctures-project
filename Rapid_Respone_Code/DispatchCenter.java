import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DispatchCenter {
    //Data Fields
    private List<Responder> responders;
    private ArrayList<Incident> allIncidents;
    private Queue<Incident> incidentQueue;

    public DispatchCenter() {
        this.responders = new ArrayList<>();
        this.incidentQueue = new LinkedList<>();
        this.allIncidents = new ArrayList<>();
    }

    // Add a responder to the system
    public void addResponder(Responder responder) {
        responders.add(responder);
    }

    // Add an incident to the system
    public void addIncident(Incident incident) {
        incidentQueue.offer(incident);  //Use offer instead of "add" for queue implementation
        allIncidents.add(incident); //Permanent record
    }

    //Getter for array of all incidents
    public ArrayList<Incident> getIncidents() {
        return allIncidents;
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

    //Quick sort algorithm for arrival
    public static void quickSort(ArrayList<ArrivalRecord> arrivalRecordsList){
        quickSort(arrivalRecordsList, 0, arrivalRecordsList.size() - 1);
    }

    public static void quickSort(ArrayList<ArrivalRecord> arrivalRecordsList, int first, int last){
        if (first < last){
            int pivotIndex = partition(arrivalRecordsList, first, last);
            quickSort(arrivalRecordsList, first, pivotIndex-1);
            quickSort(arrivalRecordsList, pivotIndex+1, last);
        }
    }

    //Partition the ArrayList
    private static int partition(ArrayList<ArrivalRecord> arrivalRecordsList, int first, int last){
        ArrivalRecord pivot = arrivalRecordsList.get(first);    //Choose the first element as the pivot
        int low = first + 1;    //Index for forward search
        int high = last;    //Index for backward search

        while (high > low) {
            //Search forward from left
            while (low <= high && arrivalRecordsList.get(low).compareTo(pivot) <= 0)
                low++;

            //Search backward from right
            while (low <= high && arrivalRecordsList.get(high).compareTo(pivot) > 0)
                high--;

            //Swap two elements in the list
            if (high > low){
                ArrivalRecord temp = arrivalRecordsList.get(high);
                arrivalRecordsList.set(high, arrivalRecordsList.get(low));
                arrivalRecordsList.set(low, temp);
            }
        }

        while(high > first && arrivalRecordsList.get(high).compareTo(pivot) >= 0){
            high--;
        }

        //Swap pivot with arrivalRecordsList.get(high)
        if (pivot.compareTo(arrivalRecordsList.get(high)) > 0){
            arrivalRecordsList.set(first, arrivalRecordsList.get(high));
            arrivalRecordsList.set(high, pivot);
            return high;    //Pivot's final position
        }
        else{
            return first;
        }
    }

    //Function for merging two sorted lists
    public static void merge(ArrayList<ArrivalRecord> firstHalf, ArrayList<ArrivalRecord> secondHalf, ArrayList<ArrivalRecord> list){
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while(current1 < firstHalf.size() && current2 < secondHalf.size()){
            //If firstHalf value at is less than secondHalf value at index, or even
            if(firstHalf.get(current1).compareTo(secondHalf.get(current2)) <= 0){
                //Set the value of the final list equal to firstHalf value
                list.set(current3, firstHalf.get(current1));
                current1++;
            } else {
                //
                list.set(current3, secondHalf.get(current2));
                current2++;
            }
            current3++;
        }

        //If every value in the secondHalf ArrayList has been added, while the firstHalf Array is non-empty add every value in the firstHalf ArrayList into the merged ArrayList
        while(current1 < firstHalf.size()){
            list.set(current3, firstHalf.get(current1));
            current1++;
            current3++;
        }

        //If every value in the firstHalf ArrayList has been added, while the secondHalf Array is non-empty add every value in array until empty
        while(current2 < secondHalf.size()){
            list.set(current3, secondHalf.get(current2));
            current2++;
            current3++;
        }
    }

    //Merge Sort Algorithm for arrival
    public static void mergeSortArrival(ArrayList<ArrivalRecord> arrivalRecordsList){
        if(arrivalRecordsList.size() > 1){
            //Calculate middle index in arrivalRecordsList for dividing list
            int mid = arrivalRecordsList.size()/2;

            //Merge Sort first half of Array
            ArrayList<ArrivalRecord> firstHalf = new ArrayList<>(arrivalRecordsList.subList(0,mid));
            mergeSortArrival(firstHalf);

            //Merge Sort Second Half
            ArrayList<ArrivalRecord> secondHalf = new ArrayList<>(arrivalRecordsList.subList(mid,arrivalRecordsList.size()));
            mergeSortArrival(secondHalf);

            //Merge both halves of list
            merge(firstHalf, secondHalf, arrivalRecordsList );

        }
    }

    // Assign available responders for a given incident
    public ArrayList<Responder> assignResponders(Incident incident) {
        ArrayList<Responder> assigned = new ArrayList<>();
        for (Responder r : responders) {
            if (r.isAvailable()) {
                // Update responder to be en route
                if (r instanceof FireResponder) r.respondToCall(incident.getLocation());
                if (r instanceof PoliceResponder) r.respondToCall(incident.getLocation());
                if (r instanceof EMSResponder) r.respondToCall(incident.getLocation());

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
            double minutes = r.calculateResponseTime();
            LocalDateTime arrivalTime = now.plusMinutes((long) minutes);

            // Create record
            arrivalOrder.add(new ArrivalRecord(r, incident, arrivalTime));
        }

        return arrivalOrder;
    }
}