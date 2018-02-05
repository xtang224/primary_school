package hello;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class LoginForm extends ActionForm {

    private String userName = null;
    private String passWord = null;   
    private String source = null;
    private String classId = null;
    
    public String getUserName() {
        return (this.userName);
    }

    public String getPassWord() {
        return (this.passWord);
    } 

    public String getSource() {
        return (this.source);
    }    

    public String getClassId() {
        return (this.classId);
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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
