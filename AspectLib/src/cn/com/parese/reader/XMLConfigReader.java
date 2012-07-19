package cn.com.parese.reader;


import cn.com.byd.factory.builder.FactoryBuilder;
import cn.com.byd.support.ILogger;
import cn.com.parese.element.BaseElement;
import cn.com.parese.element.ElementFactory;
import cn.com.parese.exception.PareseException;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**m
 * @author
 */
public enum XMLConfigReader {
	INSTANCE;
	@SuppressWarnings("compatibility:6313222217100728727")
	private static final long serialVersionUID = -7661960938868193412L;
	private static final String FILE_NAME = "config/modules_config.xml";
	private final static Object lock = new Object();
	private final static ILogger logger = FactoryBuilder.getLoggerFactory().getLogger(XMLConfigReader.class);
  public void doParese(String fileName) {
    synchronized (lock) {
      this.pareseConfig(fileName);
    }

  }
  
	public void doParese() {
		synchronized (lock) {
			this.pareseConfig(null);
		}

	}
  public void reParese() {
    synchronized (lock) {
      this.pareseConfig(null);
    }
  }
	public void reParese(String fileName) {
		synchronized (lock) {
			this.pareseConfig(fileName);
		}
	}

  

	private void pareseConfig(String fileName) {

		SAXReader reader = new SAXReader();
		Document document;

		try {
        document = reader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName == null ? FILE_NAME : fileName));

			Element root = document.getRootElement();
			Iterator<Element> it = root.elementIterator();
			Element element = null;
			while (it.hasNext()) {
				element = it.next();

				this.findElement(element.getName()).doParese(element);

			}
		} catch (DocumentException e) {
			logger.error(e);

		} catch (PareseException e) {
		    logger.error(e);
		}
	}

	private BaseElement findElement(String elementName) {
		return ElementFactory.findElement(elementName);
	}
}
