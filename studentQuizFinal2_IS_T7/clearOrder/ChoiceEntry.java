public class ChoiceEntry {
	   
    public String problem;
    public String choiceA;
    public String choiceB;
    public String choiceC;
    public String choiceD;
    public String solution;
    public String source;
    public String yourSolution = "";
    public String time = "";
    public String owner = "";
    public String rightOrWrong = "";
    public String score = "";
    public String id = "";
    
    public ChoiceEntry(String problem, String choiceA, String choiceB, String choiceC, String choiceD, String solution, String source){
    	this.problem = problem;
    	this.choiceA = choiceA;
    	this.choiceB = choiceB;
    	this.choiceC = choiceC;
    	this.choiceD = choiceD;
    	this.solution = solution;
    	this.source = source;
    }
    
    public ChoiceEntry(String problem, String choiceA, String choiceB, String choiceC, String choiceD, String solution, String source, String yourSolution, String time){
    	this.problem = problem;
    	this.choiceA = choiceA;
    	this.choiceB = choiceB;
    	this.choiceC = choiceC;
    	this.choiceD = choiceD;
    	this.solution = solution;
    	this.source = source;
    	this.yourSolution = yourSolution;
    	this.time = time;
    }
    
    public ChoiceEntry(String problem, String choiceA, String choiceB, String choiceC, String choiceD, String solution, String source, String yourSolution, String time, String owner, String rightOrWrong){
    	this.problem = problem;
    	this.choiceA = choiceA;
    	this.choiceB = choiceB;
    	this.choiceC = choiceC;
    	this.choiceD = choiceD;
    	this.solution = solution;
    	this.source = source;
    	this.yourSolution = yourSolution;
    	this.time = time;
    	this.owner = owner;
    	this.rightOrWrong = rightOrWrong;
    }
    
    public ChoiceEntry(ChoiceEntry cEntry, String source, String yourSolution, String time){
    	this.problem = cEntry.problem;
    	this.choiceA = cEntry.choiceA;
    	this.choiceB = cEntry.choiceB;
    	this.choiceC = cEntry.choiceC;
    	this.choiceD = cEntry.choiceD;
    	this.solution = cEntry.solution;
    	this.source = source;
    	this.yourSolution = yourSolution;
    	this.time = time;    
    	this.score = cEntry.score;
    	this.id = cEntry.id;
    }
    
    public ChoiceEntry(ChoiceEntry cEntry, String source, String yourSolution, String time, String owner, String rightOrWrong){
    	this.problem = cEntry.problem;
    	this.choiceA = cEntry.choiceA;
    	this.choiceB = cEntry.choiceB;
    	this.choiceC = cEntry.choiceC;
    	this.choiceD = cEntry.choiceD;
    	this.solution = cEntry.solution;
    	this.source = source;
    	this.yourSolution = yourSolution;
    	this.time = time;
    	this.owner = owner;
    	this.rightOrWrong = rightOrWrong;
    }
    
    public boolean equals(ChoiceEntry entry){
    	boolean ret = true;
    	if (!problem.trim().equalsIgnoreCase(entry.problem.trim()))
    		ret = false;
    	if (!choiceA.trim().equalsIgnoreCase(entry.choiceA.trim()))
    		ret = false;
    	if (!choiceB.trim().equalsIgnoreCase(entry.choiceB.trim()))
    		ret = false;
    	if (!choiceC.trim().equalsIgnoreCase(entry.choiceC.trim()))
    		ret = false;
    	if (!choiceD.trim().equalsIgnoreCase(entry.choiceD.trim()))
    		ret = false;
    	if (!solution.trim().equalsIgnoreCase(entry.solution.trim()))
    		ret = false;
    	if (!owner.trim().equalsIgnoreCase(entry.owner.trim()))
    		ret = false;
    	return ret;
    }
    
    public boolean equalsIgnoreOwner(ChoiceEntry entry){
    	boolean ret = true;
    	if (!problem.trim().equalsIgnoreCase(entry.problem.trim()))
    		ret = false;
    	if (!choiceA.trim().equalsIgnoreCase(entry.choiceA.trim()))
    		ret = false;
    	if (!choiceB.trim().equalsIgnoreCase(entry.choiceB.trim()))
    		ret = false;
    	if (!choiceC.trim().equalsIgnoreCase(entry.choiceC.trim()))
    		ret = false;
    	if (!choiceD.trim().equalsIgnoreCase(entry.choiceD.trim()))
    		ret = false;
    	if (!solution.trim().equalsIgnoreCase(entry.solution.trim()))
    		ret = false;    	
    	return ret;
    }
    
    public void setYourSolution(String yourSolution){
    	this.yourSolution = yourSolution;
    }
    
}
