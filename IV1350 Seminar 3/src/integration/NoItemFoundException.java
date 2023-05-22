package integration;

public class NoItemFoundException extends Exception {
    private int itemIdentifier;
    /**
     * 
    
     */
    public NoItemFoundException(int id, String errorMsg){
        super(errorMsg);
        this.itemIdentifier = id;
    }

    /**
     * Returns the id of the error
     * @return the ID that was requested for
     */
    public int getIncorrectID(){
        return this.itemIdentifier;
    }


    /**
     * errorMessage for writing to errorLogs
     * @return 	returns a string with the error message
     */
    public String errorMessage() {
        StringBuilder strBu = new StringBuilder();
        strBu.append("Item Exception - \n");
        strBu.append(this.getMessage() + "\n");
        
        return strBu.toString();
    }
}