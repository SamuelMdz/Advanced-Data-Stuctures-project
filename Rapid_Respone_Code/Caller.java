public class Caller
        implements Identifiable{
    private final String callerID;
    private final String callerName;
    private String phoneNumber;
    private String callerLocation;
    private static int numOfCallers;

    public Caller(String callerName, String phoneNumber, String callerLocation){
        this.callerID = String.format("C%04d", ++numOfCallers);
        this.callerName = callerName;
        this.phoneNumber = phoneNumber;
        this.callerLocation = callerLocation;
    }

    public String getCallerName(){
        return callerName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getCallerLocation(){
        return callerLocation;
    }

    public void updatePhoneNumber(String newPhoneNumber){
        this.phoneNumber = newPhoneNumber;
    }

    public void updateCallerLocation(String newLocation){
        this.callerLocation = newLocation;
    }

    public int getNumOfCallers(){
        return numOfCallers;
    }

    @Override
    public String getId(){
        return callerID;
    }

    @Override
    public String toString(){
        return "Caller Name: " + callerID + "; Name: " + "; Phone Number: " + phoneNumber + "; Last Reported Location: " + callerLocation;
    }
}