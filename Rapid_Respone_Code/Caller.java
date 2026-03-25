public class Caller
        implements Identifiable{
    private final String callerID;
    private final String callerName;
    private String phoneNumber;
    private String callerLocation;
    private static int numOfCallers;

    public Caller(String callerName, String phoneNumber, String callerLocation){
        this.callerID = String.format("C%04d", ++numOfCallers); //Unique ID generation
        this.callerName = callerName;
        this.phoneNumber = phoneNumber;
        this.callerLocation = callerLocation;
    }

    //Getter for callerName
    public String getCallerName(){
        return callerName;
    }

    //Getter for phoneNumber
    public String getPhoneNumber(){
        return phoneNumber;
    }

    //Getter for callerLocation
    public String getCallerLocation(){
        return callerLocation;
    }

    //Setter for phoneNumber
    public void updatePhoneNumber(String newPhoneNumber){
        this.phoneNumber = newPhoneNumber;
    }

    //Setter for callerLocation
    public void updateCallerLocation(String newLocation){
        this.callerLocation = newLocation;
    }

    //Getter for static numOfCallers variable
    public static int getNumOfCallers(){
        return numOfCallers;
    }

    //Implementation of abstract getId() method
    @Override
    public String getId(){
        return callerID;
    }

    //toString() method for readability
    @Override
    public String toString(){
        return "Caller ID: " + callerID + "; Name: " + callerName + "; Phone Number: " + phoneNumber + "; Last Reported Location: " + callerLocation;
    }
}