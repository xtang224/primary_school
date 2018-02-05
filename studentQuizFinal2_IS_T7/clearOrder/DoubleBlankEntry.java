public class DoubleBlankEntry {
	   
    public String statement_1st;
    public String statement_2nd;
    public String statement_3rd;
    public String solution_1st;
    public String solution_2nd;
    public String source;    
    
    public String yourSolution_1st = "";
    public String yourSolution_2nd = "";
    public String time = "";
    public String owner = "";
    public String rightOrWrong = "";
    
    public String score = "";
    public String id = "";
    
    public DoubleBlankEntry(String statement_1st, String statement_2nd, String statement_3rd, String solution_1st, String solution_2nd, String source){
    	this.statement_1st = statement_1st;
    	this.statement_2nd = statement_2nd;
    	this.statement_3rd = statement_3rd;
    	this.solution_1st = solution_1st;
    	this.solution_2nd = solution_2nd;    	
    	this.source = source;
    }
    
    public DoubleBlankEntry(String statement_1st, String statement_2nd, String statement_3rd, String solution_1st, String solution_2nd, String source, String yourSolution_1st, String yourSolution_2nd, String time, String owner, String rightOrWrong){
    	this.statement_1st = statement_1st;
    	this.statement_2nd = statement_2nd;
    	this.statement_3rd = statement_3rd;
    	this.solution_1st = solution_1st;
    	this.solution_2nd = solution_2nd;    	
    	this.source = source;
    	
    	this.yourSolution_1st = yourSolution_1st;
    	this.yourSolution_2nd = yourSolution_2nd;
    	this.time = time;
    	this.owner = owner;
    	this.rightOrWrong = rightOrWrong;
    }
    
    public DoubleBlankEntry(DoubleBlankEntry dbEntry, String source, String yourSolution_1st, String yourSolution_2nd, String time, String owner, String rightOrWrong){
    	this.statement_1st = dbEntry.statement_1st;
    	this.statement_2nd = dbEntry.statement_2nd;
    	this.statement_3rd = dbEntry.statement_3rd;
    	this.solution_1st = dbEntry.solution_1st;
    	this.solution_2nd = dbEntry.solution_2nd;    	
    	
    	this.source = source;    	
    	this.yourSolution_1st = yourSolution_1st;
    	this.yourSolution_2nd = yourSolution_2nd;
    	this.time = time;
    	this.owner = owner;
    	this.rightOrWrong = rightOrWrong;
    	
    	this.score = dbEntry.score; 
    	this.id = dbEntry.id;
    }
    
    public boolean equalsIgnoreOwner(DoubleBlankEntry entry){
    	boolean ret = true;
    	if (!statement_1st.trim().equalsIgnoreCase(entry.statement_1st.trim()))
    		ret = false;
    	if (!statement_2nd.trim().equalsIgnoreCase(entry.statement_2nd.trim()))
    		ret = false;
    	if (!statement_3rd.trim().equalsIgnoreCase(entry.statement_3rd.trim()))
    		ret = false;
    	if (!solution_1st.trim().equalsIgnoreCase(entry.solution_1st.trim()))
    		ret = false;
    	if (!solution_2nd.trim().equalsIgnoreCase(entry.solution_2nd.trim()))
    		ret = false;    	
    	return ret;
    }
    
    public boolean equals(DoubleBlankEntry entry){
    	boolean ret = true;
    	if (!this.equalsIgnoreOwner(entry))
    		ret = false;    	
    	if (!owner.trim().equalsIgnoreCase(entry.owner.trim()))
    		ret = false;  	
    	return ret;
    }    
}
