package hello;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.*;

public final class SmartRandomCheckForm extends ActionForm {

    private String userName = null; 
    private String passWord = null;
    private String trueName = null;
    private String id = null;       
    private String inputAnswer = null;
    private String inputAnswer2 = null;
    private String inputAnswer3 = null;
    private String inputAnswer4 = null;
    private String inputAnswer5 = null;
    private String inputAnswer6 = null;
    private String inputAnswer7 = null;
    private String correctAnswer = null; 
    private String correctAnswer_1st = null; 
    private String correctAnswer_2nd = null; 
    private String solution = null;   
    private String answeredProblems = null; 
    private String correctAnswers = null;
    private String answeredHashSet = null;
    private String answeredHashSet_tf = null;
    private String answered_M_HashSet = null;
    private String answered_MY_HashSet = null;
    private String answeredHashSet_fb = null;
    private String answeredHashSet_fdb = null;
    private String answeredHashSet_tm = null;
    private String answered_D_HashSet = null;
    private String answered_C_HashSet = null;
    private String type = null;

    private String totalScore = null;
    private String correctAnswers_low = null;
    private String answeredProblems_low = null;
    private String correctAnswers_middle = null;
    private String answeredProblems_middle = null;
    private String correctAnswers_high = null;
    private String answeredProblems_high = null;

    private String lastType = null;
    private String thisType = null;
    private String continueRight = null;
    private String continueWrong = null;
    private String neverHigh = null;

    private String times = null;
    private String randomNumber = null;

    private String statement_1st = null;
    private String statement_2nd = null;

    private String planStatus = null;
    
    private String source = null;
    private String level = null;

    private String statement_3rd = null;

    private String tmpStr = null;

    private String factor1 = null;
    private String factor2 = null;
    private String process1 = null;
    private String process2 = null;
    private String result = null;
    private String p1Solution = null;
    private String p2Solution = null;
    private String rSolution = null;
    private String resultSolution = null;

    private String product1 = null;
    private String remain1 = null;  
    private String product2 = null;
    private String remain2 = null;
    private String product3 = null;
    private String remain3 = null;   

    private String answerSentences = null;
    private String answerWords = null;
    private String fullScore = null;
    private String period = null;

    public String getUserName() {
        return (this.userName);
    }

    public String getPassWord() {
        return (this.passWord);
    } 

    public String getTrueName() {
        return (this.trueName);
    }   

    public String getId() {
        return (this.id);
    }

    public String getInputAnswer() {
        return (this.inputAnswer);
    }

    public String getInputAnswer2() {
        return (this.inputAnswer2);
    }

    public String getInputAnswer3() {
        return (this.inputAnswer3);
    }

    public String getInputAnswer4() {
        return (this.inputAnswer4);
    }

    public String getInputAnswer5() {
        return (this.inputAnswer5);
    }

    public String getInputAnswer6() {
        return (this.inputAnswer6);
    }

    public String getInputAnswer7() {
        return (this.inputAnswer7);
    }

    public String getCorrectAnswer() {
        return (this.correctAnswer);
    }

    public String getCorrectAnswer_1st() {
        return (this.correctAnswer_1st);
    }

    public String getCorrectAnswer_2nd() {
        return (this.correctAnswer_2nd);
    }  

    public String getSolution() {
        return (this.solution);
    }

    public String getAnsweredProblems() {
        return (this.answeredProblems);
    }

    public String getCorrectAnswers() {
        return (this.correctAnswers);
    }

    public String getAnsweredHashSet() {
        return (this.answeredHashSet);
    }

    public String getAnsweredHashSet_tf() {
        return (this.answeredHashSet_tf);
    }

    public String getAnswered_M_HashSet() {
        return (this.answered_M_HashSet);
    }

    public String getAnswered_MY_HashSet() {
        return (this.answered_MY_HashSet);
    }

    public String getAnsweredHashSet_fb() {
        return (this.answeredHashSet_fb);
    }

    public String getAnsweredHashSet_fdb() {
        return (this.answeredHashSet_fdb);
    }

    public String getAnsweredHashSet_tm() {
        return (this.answeredHashSet_tm);
    }

    public String getAnswered_D_HashSet() {
        return (this.answered_D_HashSet);
    }

    public String getAnswered_C_HashSet() {
        return (this.answered_C_HashSet);
    }

    public String getType() {
        return (this.type);
    }

    public String getTotalScore() {
        return (this.totalScore);
    }

    public String getCorrectAnswers_low() {
        return this.correctAnswers_low;
    }

    public String getAnsweredProblems_low() {
        return this.answeredProblems_low;
    }

    public String getCorrectAnswers_middle() {
        return this.correctAnswers_middle;
    }

    public String getAnsweredProblems_middle() {
        return this.answeredProblems_middle;
    }

    public String getCorrectAnswers_high() {
        return this.correctAnswers_high;
    }

    public String getAnsweredProblems_high() {
        return this.answeredProblems_high;
    }

    public String getLastType() {
        return this.lastType;
    }

    public String getThisType() {
        return this.thisType;
    }

    public String getContinueRight() {
        return this.continueRight;
    }

    public String getContinueWrong() {
        return this.continueWrong;
    }

    public String getNeverHigh() {
        return this.neverHigh;
    }

    public String getTimes() {
        return this.times;
    }

    public String getRandomNumber() {
        return this.randomNumber;
    }

    public String getStatement_1st() {
        return this.statement_1st;
    }

    public String getStatement_2nd() {
        return this.statement_2nd;
    }

    public String getPlanStatus() {
        return this.planStatus;
    }

    public String getSource() {
        return this.source;
    }

    public String getLevel() {
        return this.level;
    }

    public String getStatement_3rd() {
        return this.statement_3rd;
    }
      
    public String getTmpStr() {
        return this.tmpStr;
    }

    public String getFactor1() {
        return this.factor1;
    }

    public String getFactor2() {
        return this.factor2;
    }

    public String getProcess1() {
        return this.process1;
    }

    public String getProcess2() {
        return this.process2;
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

    public String getAnswerSentences() {
        return this.answerSentences;
    } 

    public String getAnswerWords() {
        return this.answerWords;
    }

    public String getFullScore() {
        return this.fullScore;
    }

    public String getPeriod() {
        return this.period;
    } 
/***********************************/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInputAnswer(String inputAnswer) {
        this.inputAnswer = inputAnswer;
    }

    public void setInputAnswer2(String inputAnswer2) {
        this.inputAnswer2 = inputAnswer2;
    }

    public void setInputAnswer3(String inputAnswer3) {
        this.inputAnswer3 = inputAnswer3;
    }

    public void setInputAnswer4(String inputAnswer4) {
        this.inputAnswer4 = inputAnswer4;
    }

    public void setInputAnswer5(String inputAnswer5) {
        this.inputAnswer5 = inputAnswer5;
    }

    public void setInputAnswer6(String inputAnswer6) {
        this.inputAnswer6 = inputAnswer6;
    }

    public void setInputAnswer7(String inputAnswer7) {
        this.inputAnswer7 = inputAnswer7;
    }
   
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCorrectAnswer_1st(String correctAnswer_1st) {
        this.correctAnswer_1st = correctAnswer_1st;
    }

    public void setCorrectAnswer_2nd(String correctAnswer_2nd) {
        this.correctAnswer_2nd = correctAnswer_2nd;
    } 

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setAnsweredProblems(String answeredProblems) {
        this.answeredProblems = answeredProblems;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setAnsweredHashSet(String answeredHashSet) {
        this.answeredHashSet = answeredHashSet;
    }

    public void setAnsweredHashSet_tf(String answeredHashSet_tf) {
        this.answeredHashSet_tf = answeredHashSet_tf;
    }

    public void setAnswered_M_HashSet(String answered_M_HashSet) {
        this.answered_M_HashSet = answered_M_HashSet;
    }

    public void setAnswered_MY_HashSet(String answered_MY_HashSet) {
        this.answered_MY_HashSet = answered_MY_HashSet;
    }

    public void setAnsweredHashSet_fb(String answeredHashSet_fb) {
        this.answeredHashSet_fb = answeredHashSet_fb;
    }

    public void setAnsweredHashSet_fdb(String answeredHashSet_fdb) {
        this.answeredHashSet_fdb = answeredHashSet_fdb;
    }

    public void setAnsweredHashSet_tm(String answeredHashSet_tm) {
        this.answeredHashSet_tm = answeredHashSet_tm;
    }

    public void setAnswered_D_HashSet(String answered_D_HashSet) {
        this.answered_D_HashSet = answered_D_HashSet;
    }

    public void setAnswered_C_HashSet(String answered_C_HashSet) {
        this.answered_C_HashSet = answered_C_HashSet;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public void setCorrectAnswers_low(String correctAnswers_low) {
        this.correctAnswers_low = correctAnswers_low;
    }

    public void setAnsweredProblems_low(String answeredProblems_low) {
        this.answeredProblems_low = answeredProblems_low;
    }

    public void setCorrectAnswers_middle(String correctAnswers_middle) {
        this.correctAnswers_middle = correctAnswers_middle;
    }

    public void setAnsweredProblems_middle(String answeredProblems_middle) {
        this.answeredProblems_middle = answeredProblems_middle;
    }
    
    public void setCorrectAnswers_high(String correctAnswers_high) {
        this.correctAnswers_high = correctAnswers_high;
    }

    public void setAnsweredProblems_high(String answeredProblems_high) {
        this.answeredProblems_high = answeredProblems_high;
    }

    public void setLastType(String lastType) {
        this.lastType = lastType;
    }

    public void setThisType(String thisType) {
        this.thisType = thisType;
    }

    public void setContinueRight(String continueRight) {
        this.continueRight = continueRight;
    }

    public void setContinueWrong(String continueWrong) {
        this.continueWrong = continueWrong;
    }

    public void setNeverHigh(String neverHigh) {
        this.neverHigh = neverHigh;
    }  

    public void setTimes(String times) {
        this.times = times;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    } 
   
    public void setStatement_1st(String statement_1st) {
        this.statement_1st = statement_1st;
    }

    public void setStatement_2nd(String statement_2nd) {
        this.statement_2nd = statement_2nd;
    } 

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setStatement_3rd(String statement_3rd) {
        this.statement_3rd = statement_3rd;
    }

    public void setTmpStr(String tmpStr) {
        this.tmpStr = tmpStr;
    }

    public void setFactor1(String factor1) {
        this.factor1 = factor1;
    }

    public void setFactor2(String factor2) {
        this.factor2 = factor2;
    }

    public void setProcess1(String process1) {
        this.process1 = process1;
    }

    public void setProcess2(String process2) {
        this.process2 = process2;
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

    public void setAnswerSentences(String answerSentences) {
        this.answerSentences = answerSentences;
    }

    public void setAnswerWords(String answerWords) {
        this.answerWords = answerWords;
    }

    public void setFullScore(String fullScore) {
        this.fullScore = fullScore;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Reset all properties to their default values.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.userName = null;
    }

    /**
     * Validate the properties posted in this request. If validation errors are
     * found, return an <code>ActionErrors</code> object containing the errors.
     * If no validation errors occur, return <code>null</code> or an empty
     * <code>ActionErrors</code> object.
     */
    /*
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        if ((userName == null) || (userName.length() < 1))
            errors.add("username", new ActionMessage("hello.no.username.error"));

        return errors;
    }
    */
}
