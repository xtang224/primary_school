package hello;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class ScoreForm extends ActionForm {

    private String userName = null;
    private String playerId = null;
    private String passWord = null;
    private String trueName = null;   
    private String vote = null;
    
    public String getUserName() {
        return (this.userName);
    }

    public String getPlayerId() {
        return (this.playerId);
    }

    public String getPassWord() {
        return (this.passWord);
    }   

    public String getTrueName() {
        return (this.trueName);
    }       

    public String getVote() {
        return (this.vote);
    }    

/********************************************/    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
   
    public void setVote(String vote) {
        this.vote = vote;
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
