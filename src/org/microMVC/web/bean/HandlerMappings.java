package org.microMVC.web.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.DocumentException;
import org.microMVC.web.factory.ObjectFactory;
import org.microMVC.web.parser.HandlerParser;
import org.microMVC.web.util.BeanUtil;

/**   
* @Description: ������ӳ��������Ҫӳ��Web�ĸ�������HandlerMappings����һ��hashMap���ڱ��洦������url��ӳ���ϵ��
* 				���Ҳ��õ���ģʽʹ����web������ֻ����һ��HandlerMappings��
* @author tanjq
* @version V1.0   
*/
public class HandlerMappings {
	private static HandlerMappings handlerMappings = new HandlerMappings();
	private Map<String, XMLHandler> handlerMap = new HashMap<String, XMLHandler>();
	private HandlerParser handlerParser = new HandlerParser();
	private Map<String, String> requestDataMap; 
	private Map<String, String> sessionDataMap;
	private ObjectFactory objectFactory;

	private HandlerMappings() {
	}

	public static HandlerMappings getInstance() {
		return handlerMappings;
	}

	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}
	
	public Map<String, String> getRequestDataMap() {
		return requestDataMap;
	}

	public Map<String, String> getSessionDataMap() {
		return sessionDataMap;
	}

	public void setSessionDataMap(Map<String, String> sessionDataMap) {
		this.sessionDataMap = sessionDataMap;
	}
	
	/**
	 * init: ��ʼ��xml���õ����е�handler
	 * @param configFile�� xml�ļ���·��
	 * @throws DocumentException: void
	 */
	public void init(String configFile) throws DocumentException {
		handlerParser.init(configFile);
		handlerMap = handlerParser.getHandlerMap();
	}

	/**
	 * getHandler: ��HandlerMappings��ά�ֵ�handlerMap��ȡhandler
	 * @param handlerName
	 * @return: XMLHandler
	 */
	private XMLHandler getHandler(String handlerName) {
		return handlerMap.get(handlerName);
	}
	
	/**
	 * execute: �˷�������SpringMVC�е�doDispatch������HTTP����ִ��Handler����󷵻ؽ����������ݺͽ������
	 * @param requestMap�� �������map
	 * @param handlerName: handler������
	 * @return: ���ؽ��
	 * @throws Exception: String
	 */
	public String execute(Map<?, ?> requestMap, String handlerName) throws Exception {
		setRequest(requestMap);
		XMLHandler handler = getHandler(handlerName);
		String className = handler.getClassName();
		String redirectPagePath = null;
		Class<?> clazz;
		Object obj = null;
		try {
			clazz = Class.forName(className);
			obj = clazz.newInstance();
		} catch (Exception e) {
			obj = objectFactory.getInstanceByBeanName(className);
			clazz = obj.getClass();
		}
		injectData(obj);
		HandlerContext context = (HandlerContext) BeanUtil.invokeMethod(obj,handler.getMethodName());
		String result = context.getResult();
		redirectPagePath = handler.getResultMap().get(result);
		return redirectPagePath;
	}

	/**
	 * injectData: ע���������������hashMap��
	 * @param obj: void
	 */
	private void injectData(Object obj) {
		if (requestDataMap == null){
			return;
		}
		for (Entry<String, String> entry : requestDataMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			injectData(obj, key, value);
		}
	}

	private void injectData(Object obj, String fieldName, Object fieldValue) {
		BeanUtil.setBeanProperty(obj, fieldName, fieldValue);
	}

	public void setRequest(Map<?, ?> requestParameterMap) {
		Map<String, String> map = new HashMap<String, String>();
		Set<?> keySet = requestParameterMap.keySet();
		Iterator<?> iter = keySet.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String[] valueArr = (String[]) requestParameterMap.get(key);
			map.put(key, valueArr[0]);
		}
		this.requestDataMap = map;
	}

}
