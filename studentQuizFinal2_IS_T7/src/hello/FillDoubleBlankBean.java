package hello;
public class FillDoubleBlankBean {

    private String userName = null;
    private int id = 0;
    private String statement_1st = null;
    private String statement_2nd = null;
    private String statement_3rd = null;
    private String choice = null;    
    private String solution_1st = null;
    private String solution_2nd = null;
    private String type = null;
    private String source = null;

    private String yourSolution_1st = null;
    private String yourSolution_2nd = null;

    public String getUserName() {
        return this.userName;
    }

    public int getId() {
        return this.id;
    }

    public String getStatement_1st() {
        return this.statement_1st;
    }

    public String getStatement_2nd() {
        return this.statement_2nd;
    }

    public String getStatement_3rd() {
        return this.statement_3rd;
    }

    public String getChoice() {
        return this.choice;
    }    

    public String getSolution_1st() {
        return this.solution_1st;
    }

    public String getSolution_2nd() {
        return this.solution_2nd;
    }

    public String getType() {
        return this.type;
    }

    public String getSource() {
        return this.source;
    }

    public String getYourSolution_1st() {
        return this.yourSolution_1st;
    }

    public String getYourSolution_2nd() {
        return this.yourSolution_2nd;
    }

/*************************************************************/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatement_1st(String statement_1st) {
        this.statement_1st = statement_1st;
    }

    public void setStatement_2nd(String statement_2nd) {
        this.statement_2nd = statement_2nd;
    }

    public void setStatement_3rd(String statement_3rd) {
        this.statement_3rd = statement_3rd;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setSolution_1st(String solution_1st) {
        this.solution_1st = solution_1st;
    }

    public void setSolution_2nd(String solution_2nd) {
        this.solution_2nd = solution_2nd;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setYourSolution_1st(String yourSolution_1st) {
        this.yourSolution_1st = yourSolution_1st;
    }

    public void setYourSolution_2nd(String yourSolution_2nd) {
        this.yourSolution_2nd = yourSolution_2nd;
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
