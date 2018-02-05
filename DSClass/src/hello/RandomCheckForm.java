package hello;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.*;

public final class RandomCheckForm extends ActionForm {

    private String userName = null; 
    private String passWord = null;
    private String trueName = null;
    private int id = 0;       
    private String inputAnswer = null;
    private String correctAnswer = null;   
    private String solution = null;   
    private String answeredProblems = null; 
    private String correctAnswers = null;
    private String answeredHashSet = null;
    private String answeredHashSet_tf = null; 

    private String totalHashSet = null;
    private String totalHashSet_tf = null;

    private String times = null;
    private String randomNumber = null;
    private String questionUsedUp = null;

    public String getUserName() {
        return (this.userName);
    }

    public String getPassWord() {
        return (this.passWord);
    } 

    public String getTrueName() {
        return (this.trueName);
    }   

    public int getId() {
        return (this.id);
    }

    public String getInputAnswer() {
        return (this.inputAnswer);
    }

    public String getCorrectAnswer() {
        return (this.correctAnswer);
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

    public String getTotalHashSet() {
        return (this.totalHashSet);
    }

    public String getTotalHashSet_tf() {
        return (this.totalHashSet_tf);
    }

    public String getTimes() {
        return (this.times);
    }

    public String getRandomNumber() {
        return (this.randomNumber);
    }

    public String getQuestionUsedUp() {
        return this.questionUsedUp;
    } 
/**********************************************************************/

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInputAnswer(String inputAnswer) {
        this.inputAnswer = inputAnswer;
    }
   
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
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

    public void setTotalHashSet(String totalHashSet) {
        this.totalHashSet = totalHashSet;
    }

    public void setTotalHashSet_tf(String totalHashSet_tf) {
        this.totalHashSet_tf = totalHashSet_tf;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public void setQuestionUsedUp(String questionUsedUp) {
        this.questionUsedUp = questionUsedUp;
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
