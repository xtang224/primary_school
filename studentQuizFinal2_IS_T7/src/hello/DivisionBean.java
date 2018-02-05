package hello;

public class DivisionBean {

    private String divided = null;
    private String divider = null;
    private String b_result = "___(                          )";   
    private String line1 = "--________";
    private String division_line = "/";
    private String b_product1 = "___(                          )";    
    private String line2 = "__________";
    private String b_remain1 = "___(                          )";
    private String b_product2 = "___(                          )"; 
    private String line3 = "__________";
    private String b_remain2 = "___(                          )";
    private String b_product3 = "___(                          )";
    private String line4 = "__________"; 
    private String b_remain3 = "___(                          )";
    private String result = null;
    private String product1 = null;
    private String remain1 = null;
    private String product2 = null;
    private String remain2 = null;
    private String product3 = null;
    private String remain3 = null;
    private int level = 3;
    private String type = null;
    private String source = null;

    private String yourResult = null;
    private String yourProduct1 = null;
    private String yourRemain1 = null;
    private String yourProduct2 = null;
    private String yourRemain2 = null;
    private String yourProduct3 = null;
    private String yourRemain3 = null;
    
    private int id = 0;

    public DivisionBean(){
       line1 = "--________";
       line2 = "__________";
       line3 = "__________";
       line4 = "__________"; 
       division_line = "/";
    }
   
    public String getDivided() {
        return this.divided;
    }

    public String getDivider() {
        return this.divider;
    } 

    public String getB_result() {
        return this.b_result;
    }

    public String getB_product1() {
        return this.b_product1;
    }

    public String getB_remain1() {
        return this.b_remain1;
    }  

    public String getB_product2() {
        return this.b_product2;
    }

    public String getB_remain2() {
        return this.b_remain2;
    }

    public String getB_product3() {
        return this.b_product3;
    }

    public String getLine1(){
        return this.line1;
    }

    public String getDivision_line(){
        return this.division_line;
    }

    public String getLine2(){
        return this.line2;
    }

    public String getLine3(){
        return this.line3;
    }

    public String getLine4(){
        return this.line4;
    }

    public String getB_remain3() {
        return this.b_remain3;
    }

    public String getResult() {
        return this.result;
    }    

    public String getProduct1() {
        return this.product1;
    } 

    public String getRemain1() {
        return this.remain1;
    } 

    public String getProduct2() {
        return this.product2;
    }  

    public String getRemain2() {
        return this.remain2;
    }

    public String getProduct3() {
        return this.product3;
    }  

    public String getRemain3() {
        return this.remain3;
    }

    public int getLevel() {
        return this.level;
    }

    public String getType() {
        return this.type;
    }

    public String getSource() {
        return this.source;
    }

    public String getYourResult() {
        return this.yourResult;
    }

    public String getYourProduct1() {
        return this.yourProduct1;
    }

    public String getYourRemain1() {
        return this.yourRemain1;
    }

    public String getYourProduct2() {
        return this.yourProduct2;
    }

    public String getYourRemain2() {
        return this.yourRemain2;
    }

    public String getYourProduct3() {
        return this.yourProduct3;
    }

    public String getYourRemain3() {
        return this.yourRemain3;
    }

    public int getId() {
        return this.id;
    }
   
/*************************************************************/
    public void setDivided(String divided) {
        this.divided = divided;
    }   

    public void setDivider(String divider) {
        this.divider = divider;
    }   

    public void setB_result(String b_result) {
        this.b_result = b_result;
    }

    public void setB_product1(String b_product1) {
        this.b_product1 = b_product1;
    } 

    public void setB_remain1(String b_remain1) {
        this.b_remain1 = b_remain1;
    } 

    public void setB_product2(String b_product2) {
        this.b_product2 = b_product2;
    } 

    public void setB_remain2(String b_remain2) {
        this.b_remain2 = b_remain2;
    } 

    public void setB_product3(String b_product3) {
        this.b_product3 = b_product3;
    } 

    public void setB_remain3(String b_remain3) {
        this.b_remain3 = b_remain3;
    } 

    public void setResult(String result) {
        this.result = result;
    }

    public void setProduct1(String product1) {
        this.product1 = product1;
    }

    public void setRemain1(String remain1) {
        this.remain1 = remain1;
    }

    public void setProduct2(String product2) {
        this.product2 = product2;
    }

    public void setRemain2(String remain2) {
        this.remain2 = remain2;
    }

    public void setProduct3(String product3) {
        this.product3 = product3;
    }

    public void setRemain3(String remain3) {
        this.remain3 = remain3;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setYourResult(String yourResult) {
        this.yourResult = yourResult;
    }

    public void setYourProduct1(String yourProduct1) {
        this.yourProduct1 = yourProduct1;
    }

    public void setYourRemain1(String yourRemain1) {
        this.yourRemain1 = yourRemain1;
    }

    public void setYourProduct2(String yourProduct2) {
        this.yourProduct2 = yourProduct2;
    }

    public void setYourRemain2(String yourRemain2) {
        this.yourRemain2 = yourRemain2;
    }

    public void setYourProduct3(String yourProduct3) {
        this.yourProduct3 = yourProduct3;
    }

    public void setYourRemain3(String yourRemain3) {
        this.yourRemain3 = yourRemain3;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void saveToPersistentStore() {
        
    }
}
