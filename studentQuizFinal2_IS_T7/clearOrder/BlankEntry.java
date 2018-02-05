public class BlankEntry {
	   
    public String statement_1st;
    public String statement_2nd;    
    public String solution;
    public String source; 
    
    public String yourSolution = "";
    public String time = "";
    public String owner = "";
    public String rightOrWrong = "";
    
    public String score = "";
    public String id = "";
    
    public BlankEntry(String statement_1st, String statement_2nd, String solution, String source){
    	this.statement_1st = statement_1st;
    	this.statement_2nd = statement_2nd;    	
    	this.solution = solution;    	    	
    	this.source = source;
    }
    
    public BlankEntry(String statement_1st, String statement_2nd, String solution, String source, String yourSolution, String time, String owner, String rightOrWrong){
    	this.statement_1st = statement_1st;
    	this.statement_2nd = statement_2nd;    	
    	this.solution = solution;    	    	
    	this.source = source;
    	
    	this.yourSolution = yourSolution;    	
    	this.time = time;
    	this.owner = owner;
    	this.rightOrWrong = rightOrWrong;
    }
    
    public BlankEntry(BlankEntry bEntry, String source, String yourSolution, String time, String owner, String rightOrWrong){
    	this.statement_1st = bEntry.statement_1st;
    	this.statement_2nd = bEntry.statement_2nd;    	
    	this.solution = bEntry.solution;    	 
    	
    	this.source = source;    	
    	this.yourSolution = yourSolution;
    	this.time = time;
    	this.owner = owner;
    	this.rightOrWrong = rightOrWrong;
    	
    	this.score = bEntry.score;
    	this.id = bEntry.id;
    }
    
    public boolean equalsIgnoreOwner(BlankEntry entry){
    	boolean ret = true;
    	if (!statement_1st.trim().equalsIgnoreCase(entry.statement_1st.trim()))
    		ret = false;
    	if (!statement_2nd.trim().equalsIgnoreCase(entry.statement_2nd.trim()))
    		ret = false;    	
    	if (!solution.trim().equalsIgnoreCase(entry.solution.trim()))
    		ret = false;    	
    	return ret;
    }
    
    public boolean equals(BlankEntry entry){
    	boolean ret = true;
    	if (!this.equalsIgnoreOwner(entry))
    		ret = false;    	
    	if (!owner.trim().equalsIgnoreCase(entry.owner.trim()))
    		ret = false;  	
    	return ret;
    }    
}
