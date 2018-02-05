package hello;
public class ChoiceBean {

    private String userName = null;
    private int id = 0;
    private String statement = null;
    private String choice = null;    
    private String solution = null;    

    public String getUserName() {
        return this.userName;
    }

    public int getId() {
        return this.id;
    }

    public String getStatement() {
        return this.statement;
    }

    public String getChoice() {
        return this.choice;
    }    

    public String getSolution() {
        return this.solution;
    }
   
/*************************************************************/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    
    /**
     * This is a stub method that would be used for the Model to save
     * the information submitted to a persistent store. In this sample
     * application it is not used.
     */
    public void saveToPersistentStore() {

        /*
         * This is a stub method that might be used to save the person's
         * name to a persistent store(i.e. database) if this were a real application.
         *
         * The actual business operations that would exist within a Model
         * component would depend upon the requirements of the application.
         */
    }
}