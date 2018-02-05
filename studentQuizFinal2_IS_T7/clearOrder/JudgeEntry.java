public class JudgeEntry {
	   
    public String statement;
    public String solution;
    public String source;
    public String yourSolution = "";
    public String time = "";
    public String owner = "";
    public String rightOrWrong = "";
    public String score = "";
    public String id = "";
    
    public JudgeEntry(String statement, String solution, String source){
    	this.statement = statement;
    	this.solution = solution;
    	this.source = source;
    }
    
    public JudgeEntry(String statement, String solution, String source, String yourSolution, String time, String owner, String rightOrWrong){
    	this.statement = statement;
    	this.solution = solution;
    	this.source = source;
    	this.yourSolution = yourSolution;
    	this.time = time;
    	this.owner = owner;
    	this.rightOrWrong = rightOrWrong;
    }
    
    public JudgeEntry(JudgeEntry entry, String source, String yourSolution, String time, String owner, String rightOrWrong){
    	this.statement = entry.statement;
    	this.solution = entry.solution;    	
    	this.source = source;
    	this.yourSolution = yourSolution;
    	this.time = time;
    	this.owner = owner;
    	this.rightOrWrong = rightOrWrong;
    	this.score = entry.score; 
    	this.id = entry.id;
    }
    
    public boolean equalsIgnoreOwner(JudgeEntry entry){
    	boolean ret = true;
    	if (!this.statement.equals(entry.statement))
    		ret = false;
    	if (!this.solution.equals(entry.solution))
    		ret = false;    	
    	return ret;
    }
    
    public boolean equals(JudgeEntry entry){
    	boolean ret = true;
    	if (!this.statement.equals(entry.statement))
    		ret = false;
    	if (!this.solution.equals(entry.solution))
    		ret = false;    	
    	if (!this.owner.equals(entry.owner))
    		ret = false;
    	return ret;
    }
}
