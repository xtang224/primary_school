package hello;
public class TermMatchBean {

    private String userName = null;
    private int id = 0;
    private String term1 = null;
    private String term2 = null;    
    private String type = null;
    private String source = null;    

    public String getUserName() {
        return this.userName;
    }

    public int getId() {
        return this.id;
    }

    public String getTerm1() {
        return this.term1;
    }

    public String getTerm2() {
        return this.term2;
    }

    public String getType() {
        return this.type;
    }    

    public String getSource() {
        return this.source;
    }
   
/*************************************************************/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTerm1(String term1) {
        this.term1 = term1;
    }

    public void setTerm2(String term2) {
        this.term2 = term2;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setSource(String source) {
        this.source = source;
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
