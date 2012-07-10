/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author kete
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    /** This is a sample web service operation */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
