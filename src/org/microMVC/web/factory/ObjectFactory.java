package org.microMVC.web.factory;

import org.dom4j.DocumentException;


/**   
* @Description: �����ӿ�
* @author tanjq
* @version V1.0   
*/
public interface ObjectFactory {
	
	/**
	 * init: ��ʼ��bean
	 * @param configFile: xml�ļ���·��
	 * @throws DocumentException: void
	 */
	public void init(String configFile) throws DocumentException;
	/**
	 * getInstanceByBeanName: ����bean�����ֵõ�beanʵ��
	 * @param beanName�� 
	 * @return �� beanʵ��
	 * @throws Exception: Object
	 */
	public Object getInstanceByBeanName(String beanName) throws Exception;
	/**
	 * getInstance: ��֪Ŀ���࣬����CgLib����beanʵ��
	 * @param obj
	 * @return
	 * @throws Exception: Object
	 */
	public Object getInstance(Object obj) throws Exception;
}

