package org.microMVC.web.aop;

import java.lang.reflect.Method;


/**   
* @Description: ����֪ͨ������һ���ص�������after
* @author tanjq
* @version V1.0   
*/
public interface AfterAdvice {
	void after(Class<?> targetClass,Object proxyObj, Method method, Object[] args);
}
