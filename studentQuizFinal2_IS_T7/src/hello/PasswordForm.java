package hello;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class PasswordForm extends ActionForm {

    private String userName = null;    
    private String trueName = null; 
    private String passWord = null;
    private String oldPassword = null;   
    private String newPassword = null; 
    private String confirmNewPassword = null;  
    
    public String getUserName() {
        return (this.userName);
    }    

    public String getTrueName() {
        return (this.trueName);
    } 

    public String getPassWord() {
        return (this.passWord);
    }  

    public String getOldPassword() {
        return (this.oldPassword);
    }    
    
    public String getNewPassword() {
        return (this.newPassword);
    }

    public String getConfirmNewPassword() {
        return (this.confirmNewPassword);
    }  

    public void setUserName(String userName) {
        this.userName = userName;
    }     

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
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
