package org.microMVC.web.servlet;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.dom4j.DocumentException;
import org.microMVC.web.bean.HandlerMappings;
import org.microMVC.web.factory.AopProxyFactory;
import org.microMVC.web.factory.ObjectFactory;

/**   
* @Description: ǰ�ο��������࣬���ڶ�λ�������ʼ����Ϣ.
* 				����ά��һ��HandlerMappings��ÿ�����󵽴�ǰ�ο�����������handlerMappings�в�ѯHandler.
* @author tanjq
* @version V1.0   
*/
public class HttpServletBean extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String contextConfigLocation;   // mvc.xml·��
	
	private String beanConfigLocation;   //���õ�bean��·��
	
	private HandlerMappings handlerMappings;
	
	public void setContextConfigLocation(String contextConfigLocation) {
		this.contextConfigLocation = contextConfigLocation;
	}

	public void setBeanConfigLocation(String beanConfigLocation) {
		this.beanConfigLocation = beanConfigLocation;
	}
	
	public void setActionMappings(HandlerMappings handlerMappings) {
		this.handlerMappings = handlerMappings;
	}

	public String getContextConfigLocation() {
		return contextConfigLocation;
	}

	public String getBeanConfigLocation() {
		return beanConfigLocation;
	}
	
	public HandlerMappings getActionMappings() {
		return handlerMappings;
	}
	
	public void initParams(ServletConfig config){
		this.contextConfigLocation = config.getInitParameter("contextConfigLocation");
		this.beanConfigLocation = config.getInitParameter("beanConfigLocation");
	}

	
	/**
	 * 
	 * initObjFactory: ��ʼ�����õ�ObjectFactory
	 * @param config�� xml�ļ�·��
	 * @return: ObjectFactory
	 */
	public ObjectFactory initObjFactory(ServletConfig config){
		if(beanConfigLocation == null || beanConfigLocation.equals("")){
			throw new RuntimeException("beanConfigLocation can not null.");
		}
		String projectPath = config.getServletContext().getRealPath("/");
		String relBeanConfigPath = projectPath +"WEB-INF"+File.separatorChar+"classes"+File.separatorChar + beanConfigLocation;
		ObjectFactory objectFactory = AopProxyFactory.getInstance();
		try {
			objectFactory.init(relBeanConfigPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return objectFactory;
	}
	
	/**
	 * 
	 * initActionMapping: ��ʼ����Action��result��Ӧ��ϵ
	 * @param config: xml�ļ���·��
	 * @param objFactory: void
	 */
	public void initActionMapping(ServletConfig config, ObjectFactory objFactory){
		if(contextConfigLocation == null || contextConfigLocation.equals("")){
			throw new RuntimeException("contextConfigLocation can not null.");
		}
		String projectPath = config.getServletContext().getRealPath("/");
		String relContentConfigPath = projectPath +"WEB-INF"+File.separatorChar+"classes"+File.separatorChar+contextConfigLocation;
		handlerMappings = HandlerMappings.getInstance();
		try {
			handlerMappings.init(relContentConfigPath);
			handlerMappings.setObjectFactory(objFactory);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
