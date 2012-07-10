/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback.PasswordValidationException;
import com.sun.xml.wss.impl.callback.PasswordValidationCallback.Request;


/**
 *
 * @author kete
 */
public class CurrentValidator implements  PasswordValidationCallback.PasswordValidator{

    public boolean validate(Request request) throws PasswordValidationException {
        PasswordValidationCallback.PlainTextPasswordRequest ptreq 
            = (PasswordValidationCallback.PlainTextPasswordRequest)request;
        return "wist".equals(ptreq.getUsername()) && "books".equals(ptreq.getPassword());
    }
    
}
