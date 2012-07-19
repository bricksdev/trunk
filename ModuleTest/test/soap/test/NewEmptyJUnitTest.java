/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.test;

//import org.codehaus.xfire.client.Client;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author kete
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public static void main(String... args) {
                    //行项目号
            List<String> proItems = new ArrayList<String>();
            String proItem = "0001";
            proItems.add(proItem);
            if (proItem.length() < 4) {
                proItems.add(String.format("%0" + (4 - proItem.length()) + "d", 0) + proItem);
            } else {
                int idx = 0;
                while (idx < proItem.length() && proItem.charAt(idx) == '0') {
                    idx++;
                }
                // update jhq1022749 2011-5-9 修复订单行项目为'0000'出现过账异常 start
                if (idx == proItem.length()) {
                    proItems.add(proItem);
                } else {
                    proItems.add(proItem.substring(idx));
                }
                // update jhq1022749 2011-5-9 修复订单行项目为'0000'出现过账异常 end
            }
            System.out.println(proItems);
//        try {
//
//            WSVendorPartsOutSoap wsVendorPartsOut = new WSVendorPartsOut().getWSVendorPartsOutSoap12();
//            Ds ds = new ObjectFactory().createVendorPartsOutDs();
//            Table1 table = new jxb.ObjectFactory().createNewDataSetTable1();
//            table.setSAP("1224");
//            table.setPartsAmt(233);
//            table.setVenCode("tee123");
//            NewDataSet dataset = new jxb.ObjectFactory().createNewDataSet();
//            dataset.getTable1().add(table);
//            Node node = SOAPFactory.newInstance().createElement(new QName("http://tempuri.org/", "jxbContext"));
//            JAXBContext con = JAXBContext.newInstance("jxb");
//
//            con.createMarshaller().marshal(dataset, node);
//
//            ds.setAny(node);
//            VendorPartsOutResult result = wsVendorPartsOut.vendorPartsOut(ds);
//            ElementNSImpl returnElement = (ElementNSImpl) result.getAny();
//            println(returnElement, new StringBuffer());
//        } catch (Exception ex) {
//            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private static void println(Node element, StringBuffer prifix) {
        NodeList nodeList = element.getChildNodes();
        if (nodeList != null) {
            int i = 0, length;
            Node node = null;
            NamedNodeMap nodeMap = null;
//            Node attr = null;
            for (i = 0, length = nodeList.getLength(); i < length; i++) {
                node = nodeList.item(i);
                System.out.println(prifix + ":" + node.getNodeName() + " " + node.getNodeValue());
                nodeMap = node.getAttributes();
                if (nodeMap != null) {
                    for (int k = 0, l = nodeMap.getLength(); k < l; k++) {
                        System.out.println(prifix + "attr:" + nodeMap.item(k).getNodeName() + " " + nodeMap.item(k).getTextContent());
                    }
                }
                println(node, new StringBuffer().append("--"));
            }
        }
    }
}
