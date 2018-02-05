package hello;
public class MultiplyBean {

    private String factor1 = null;
    private String factor2 = null;
    private String line1 = "-------------------------";
    private String process1 = null;
    private String process2 = null;
    private String line2 = "-------------------------";
    private String result = null;
    private String p1Solution = null;
    private String p2Solution = null;
    private String rSolution = null;
    private String resultSolution = null;
    private String type = null;
    private String source = null;

    private String yourSolution_1st = null;
    private String yourSolution_2nd = null;
    private String yourSolution_3rd = null;
    
    private int id = 0;
   
    public String getFactor1() {
        return this.factor1;
    }

    public String getFactor2() {
        return this.factor2;
    } 

    public String getLine1() {
        return this.line1;
    }    

    public String getProcess1() {
        return this.process1;
    } 

    public String getProcess2() {
        return this.process2;
    }  

    public String getLine2() {
        return this.line2;
    }

    public String getResult() {
        return this.result;
    } 

    public String getP1Solution() {
        return this.p1Solution;
    }     

    public String getP2Solution() {
        return this.p2Solution;
    } 

    public String getRSolution() {
        return this.rSolution;
    }

   public String getResultSolution() {
        return this.resultSolution;
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

    public String getYourSolution_3rd() {
        return this.yourSolution_3rd;
    }

    public int getId() {
        return this.id;
    }
   
/*************************************************************/
    public void setFactor1(String factor1) {
        this.factor1 = factor1;
    }   

    public void setFactor2(String factor2) {
        this.factor2 = factor2;
    }     

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setProcess1(String process1) {
        this.process1 = process1;
    }

    public void setProcess2(String process2) {
        this.process2 = process2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setP1Solution(String p1Solution) {
        this.p1Solution = p1Solution;
    }

    public void setP2Solution(String p2Solution) {
        this.p2Solution = p2Solution;
    }

    public void setRSolution(String rSolution) {
        this.rSolution = rSolution;
    }

    public void setResultSolution(String resultSolution) {
        this.resultSolution = resultSolution;
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

    public void setYourSolution_3rd(String yourSolution_3rd) {
        this.yourSolution_3rd = yourSolution_3rd;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void saveToPersistentStore() {        
    }
}
