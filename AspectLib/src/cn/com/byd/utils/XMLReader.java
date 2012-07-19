package cn.com.byd.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public enum XMLReader {
	INSTANCE;

	/**
	 * 文件名前缀
	 */
	private static final String FILE_PRIFX = "file:/";
	private static final String TEMP_FILE_PRIFX = "TMP_";
  // 元素节点
	private static final String DOM_CONFIG_ROOT = "interfaces";
	private static final String DOM_CONFIG_INTERFACE = "interface";
  private static final String DOM_CONFIG_PROXY = "proxy";
  private static final String DOM_CONFIG_IMPLEMENT = "implement";
  private static final String DOM_CONFIG_METHODS = "methods";
  private static final String DOM_CONFIG_METHOD = "method";
  private static final String DOM_CONFIG_LOG = "log";
  private static final String DOM_CONFIG_TRANSACTION = "transaction";
  private static final String DOM_CONFIG_AUTHENTICATION = "authentication";
  private static final String DOM_CONFIG_MODULES = "modules";
  private static final String DOM_CONFIG_MODULE = "module";
  private static final String DOM_CONFIG_PARAMETER = "parameter";
  private static final String DOM_CONFIG_RESULT = "result";
  private static final String DOM_CONFIG_PROPERTY = "property";
  
  
  // 属性节点
  private static final String DOM_CONFIG_ATTR_TYPE = "type";
  private static final String DOM_CONFIG_ATTR_NAME = "name";
  private static final String DOM_CONFIG_ATTR_DIVISION = "division";
  private static final String DOM_CONFIG_ATTR_METHOD = "method";
  private static final String DOM_CONFIG_ATTR_PROCESSINDEX = "processIndex";

	/**
	 * 获取日志输出类
	 */
	private static final Log log = LoggerFactory.INSTANCE.getLogger(XMLReader.class);

	/**
	 *解析配置文件
	 * @param fileName
	 */
	private static final List<String> FILE_NAME = new ArrayList<String>();

	public static void parseConfig(String fileName) {

		pareConfig(getFile(fileName));
	}

	/**
	 *解析配置文件
	 * @param file
	 */
	public static void pareConfig(File file) {

		if (!FILE_NAME.contains(file.getName())) {
			FILE_NAME.add(file.getName());
			// 读取配置文件
			loadConfig(file);
		}
	}

	/**
	 * 登录配置文件
	 * @param file
	 */
	private static void loadConfig(File file) {
		try {
			if (file == null) {
				log.info("XML config not found!");
				return;
			}
			log.info("Loaded XML Config File Path:" + file.getPath());
			SAXReader builder = new SAXReader();
			Document doc = builder.read(file);
			Element root = doc.getRootElement();

			List<Element> interfaces = root.element(DOM_CONFIG_ROOT).elements(DOM_CONFIG_INTERFACE);

			// 获取配置列表
			for (Element tmp : interfaces) {
				//findServerConfig(tmp);
			}

		} catch (DocumentException ex) {
			log.error(ex);
//		} catch (IOException ex) {
//			log.error(ex);
		} finally {
			//file.delete();
		}
	}

	/**
	 * 获取指定文件名文件
	 * @param fileName
	 * @return
	 */
	private static File getFile(String fileName) {
		File file = null;
		try {
			URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);

			String filePath = URLDecoder.decode(url.getPath(), "UTF-8");
			file = new File(filePath);
			FileOutputStream fos = null;
			InputStream in = null;
			if (!file.exists()) {
				try {
					// 去除jar包下的特定的文件字符[!/] 获取jar实类
					JarFile currentJar = null;
					try {
						currentJar =
		   new JarFile(new File(new URI(filePath.substring(0, filePath.lastIndexOf(fileName) - 2))));
					} catch (URISyntaxException ex) {
						log.error(ex);
						currentJar =
		   new JarFile(new File(filePath.substring(FILE_PRIFX.length(), filePath.lastIndexOf(fileName) - 2)));
					}
					JarEntry entry = currentJar.getJarEntry(fileName);
					in = currentJar.getInputStream(entry);
					File tempFile = File.createTempFile(TEMP_FILE_PRIFX, fileName.split("[.]")[1]);
					fos = new FileOutputStream(tempFile, false);
					byte[] ret = new byte[1024];
					int length = -1;
					while ((length = in.read(ret)) != -1) {
						fos.write(ret, 0, length);
					}
					fos.flush();
					file = tempFile;

				} catch (IOException ex) {
					log.error(ex);
				} finally {
					try {
						if (fos != null) {
							fos.close();
						}
						if (in != null) {
							in.close();
						}
					} catch (IOException ex) {
						log.error(ex);
					}
					fos = null;
					in = null;
				}
			}

		} catch (UnsupportedEncodingException ex) {
			log.error(ex);
		}
		return file;
	}
}
