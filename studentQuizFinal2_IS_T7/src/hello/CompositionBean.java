package hello;
public class CompositionBean {

    private String statement = null;
    private String yourSentences = null;
    private String answerSentences = null;
    private String answerWords = null;    
    private String keyWords = null;
    private String type = null;
    private String source = null;
    private String comment = null;

    private int fullScore = 0;  
    private double scoreRatio = 0;  
    private int id = 0;
   
    public String getStatement() {
        return this.statement;
    }

    public String getYourSentences() {
        return this.yourSentences;
    }

    public String getAnswerSentences() {
        return this.answerSentences;
    } 

    public String getAnswerWords() {
        return this.answerWords;
    }

    public String getKeyWords() {
        return this.keyWords;
    }
    
    public String getType() {
        return this.type;
    }

    public String getSource() {
        return this.source;
    }

    public String getComment() {
        return this.comment;
    }

    public int getFullScore() {
        return this.fullScore;
    }

    public double getScoreRatio() {
        return this.scoreRatio;
    }

    public int getId() {
        return this.id;
    }
   
/*************************************************************/

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setYourSentences(String yourSentences) {
        this.yourSentences = yourSentences;
    }   

    public void setAnswerSentences(String answerSentences) {
        this.answerSentences = answerSentences;
    }     

    public void setAnswerWords(String answerWords) {
        this.answerWords = answerWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public void setScoreRatio(double scoreRatio) {
        this.scoreRatio = scoreRatio;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void saveToPersistentStore() {
        
    }
}
