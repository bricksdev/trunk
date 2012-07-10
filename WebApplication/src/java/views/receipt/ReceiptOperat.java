/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.receipt;

import byd.wms.modules.receipt.ReceiptModules;
import cn.com.factory.OperatFactory;
import java.util.Map;

/**
 *
 * @author kete
 */
public class ReceiptOperat {


    public void confirm(Map request, Map session){
        String asnNo = request.get("asnNo").toString();
        request.put("asn", OperatFactory.getServiceObject(ReceiptModules.class).queryAsn(asnNo));
    }

    public void save(Map request, Map session){

    }

}
