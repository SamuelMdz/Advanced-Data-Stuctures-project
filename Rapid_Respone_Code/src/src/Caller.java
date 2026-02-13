public class Caller
        implements Identifiable, Cloneable{
    private String callerID;
    private String callerName;
    private String phoneNumber;
    private String callerLocation;

    public Caller(String callerID, String callerName, String phoneNumber, String location){
        this.callerID = callerID;
        this.callerName = callerName;
        this.phoneNumber = phoneNumber;
        this.callerLocation = callerLocation;
    }

    public Caller(){
        this.callerID = "";
        this.callerName = "";
        this.phoneNumber = "";
        this.callerLocation = "";
    }
}
