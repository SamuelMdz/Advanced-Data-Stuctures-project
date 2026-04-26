import java.util.HashMap;
public class CallerRegistry {

    // Data Field
    private HashMap<String, Caller> callerMap;

    // Constructor
    public CallerRegistry() {
        this.callerMap = new HashMap<>();
    }

    // Add a caller to the registry
    public void addCaller(Caller caller) {
        if (caller != null) {
            callerMap.put(caller.getId(), caller);
        }
    }

    // Retrieve a caller by ID
    public Caller getCallerById(String callerId) {
        return callerMap.get(callerId);
    }

    // Remove a caller by ID
    public boolean removeCaller(String callerId) {
        if (callerMap.containsKey(callerId)) {
            callerMap.remove(callerId);
            return true;
        }
        return false;
    }

    // Update caller phone number
    public boolean updateCallerPhoneNumber(String callerId, String newPhoneNumber) {
        Caller caller = callerMap.get(callerId);

        if (caller != null) {
            caller.updatePhoneNumber(newPhoneNumber);
            return true;
        }

        return false;
    }

    // Update caller location
    public boolean updateCallerLocation(String callerId, String newLocation) {
        Caller caller = callerMap.get(callerId);

        if (caller != null) {
            caller.updateCallerLocation(newLocation);
            return true;
        }

        return false;
    }

    // Get total number of callers in registry
    public int getTotalCallers() {
        return callerMap.size();
    }

    // Print all callers (useful for testing/debugging)
    public void printAllCallers() {
        System.out.println("Caller Registry Contents:");
        for (Caller caller : callerMap.values()) {
            System.out.println(caller);
        }
    }
}
