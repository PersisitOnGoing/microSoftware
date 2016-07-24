package org.microMVC.web.parser;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.microMVC.web.bean.XMLHandler;
import org.microMVC.web.util.Constants;

/**   
* @Description: ����xml�ļ��е�Handler����������ص����ݽṹ.
* 				����servlet������Ҳֻά��һ��hashMap��������ʼ��ʱ��handler��֮��ʼ��
* @author tanjq
* @version V1.0   
*/
public class HandlerParser {
	private Map<String, XMLHandler> handlerMap = new HashMap<String, XMLHandler>();

	public Map<String, XMLHandler> getHandlerMap() {
		return handlerMap;
	}
	
	/**
	 * init: ��������ʱ��ʼ������ʼ��xml���õ����е�handler
	 * @param configFile
	 * @throws DocumentException: void
	 */
	public void init(String configFile) throws DocumentException {
		SAXReader reader = new SAXReader();

		File file = new File(configFile);
		Document document = reader.read(file);
		Element root = document.getRootElement();
		for (Iterator<?> i = root.elementIterator("package"); i.hasNext();) {
			Element packageNode = (Element) i.next();
			for (Iterator<?> j = packageNode.elementIterator("handler"); j.hasNext();) {
				XMLHandler handler = new XMLHandler();
				Element actionNode = (Element) j.next();
				String actionName = actionNode.attribute("name").getText();
				String className = actionNode.attribute("class").getText();
				String methodName = actionNode.attribute("method").getText();
				if (methodName == null){
					methodName = Constants.DEFAULT_METHOD_NAME;
				}
				handler.setClassName(className);
				handler.setName(actionName);
				handler.setMethodName(methodName);
				Map<String, String> resultMap = new HashMap<String, String>();

				for (Iterator<?> k = actionNode.elementIterator("result"); k.hasNext();) {
					Element resultNode = (Element) k.next();
					String resultName = resultNode.attribute("name").getText();
					String resultURL = resultNode.getText();
					resultMap.put(resultName, resultURL);
				}

				handler.setResultMap(resultMap);
				handlerMap.put(actionName, handler);
			}
		}
	}

}
