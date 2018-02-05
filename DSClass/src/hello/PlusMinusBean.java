package hello;
public class PlusMinusBean {

    private String factor1 = null;
    private String factor2 = null;
    private String line = "-------------------------";
    private String preResult = "(             )";
    private String result = null;
    
    private String type = null;
    private String source = null;
    private String yourSolution = "";   
    
    private int id = 0;
    private int score = 0;
   
    public String getFactor1() {
        return this.factor1;
    }

    public String getFactor2() {
        return this.factor2;
    } 

    public String getLine() {
        return this.line;
    }    

    public String getPreResult() {
        return this.preResult;
    }

    public String getResult() {
        return this.result;
    }   

    public String getType() {
        return this.type;
    }

    public String getSource() {
        return this.source;
    }

    public String getYourSolution() {
        return this.yourSolution;
    }   

    public int getId() {
        return this.id;
    }

    public int getScore() {
        return this.score;
    }
   
/*************************************************************/
    public void setFactor1(String factor1) {
        this.factor1 = factor1;
    }   

    public void setFactor2(String factor2) {
        this.factor2 = factor2;
    }     

    public void setLine(String line) {
        this.line = line;
    }

    public void setPreResult(String preResult) {
        this.preResult = preResult;
    }

    public void setResult(String result) {
        this.result = result;
    }   

    public void setType(String type) {
        this.type = type;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setYourSolution(String yourSolution) {
        this.yourSolution = yourSolution;
    }    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void saveToPersistentStore() {        
    }
}
